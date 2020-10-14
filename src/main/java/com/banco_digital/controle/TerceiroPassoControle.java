package com.banco_digital.controle;

import com.banco_digital.dao.TerceiroPassoDao;
import com.banco_digital.erros.ErroNotFound;
import com.banco_digital.modelo.cliente.TerceiroPasso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//Classe que controla o objeto do terceiro passo
@Controller
@RequestMapping("terceiro_passo")
public class TerceiroPassoControle {
    private final TerceiroPassoDao terceiroPassoDao;

    @Autowired
    public TerceiroPassoControle(TerceiroPassoDao terceiroPassoDao) {
        this.terceiroPassoDao = terceiroPassoDao;
    }

    //Metodo para listar o terceiro passo
    @GetMapping
    public ResponseEntity<?> listarTerceiroPasso() {
        return new ResponseEntity<>(terceiroPassoDao.findAll(), HttpStatus.OK);
    }

    //Metodo para buscar o terceiro passo por Id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> buscarTerceiroPasoo(@PathVariable("id") Long id) {
        TerceiroPasso terceiroPasso = terceiroPassoDao.findById(id).get();
        return new ResponseEntity<>(terceiroPasso, HttpStatus.OK);
    }

    //salva o arquivo na pasta
    private static String UPLOADED_FOLDER = "/com/banco_digital/";

    @PostMapping("/upload")
    public String uploadDaFoto(@RequestParam MultipartFile file,
                               RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "selecione um arquivo");
            return "redirect:/uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
                    "upload feito com sucesso '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "/uploadStatus";
    }

    //Metodo para atualizar o terceiro passo
    @PutMapping
    public ResponseEntity<?> atualizarSegundoPasso(@RequestBody TerceiroPasso terceiroPasso) {
        verificarSeFotoExiste(terceiroPasso.getId());
        terceiroPassoDao.save(terceiroPasso);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Metodo para verificar se a foto existe
    private void verificarSeFotoExiste(Long id) {
        if (terceiroPassoDao.findById(id).isEmpty())
            throw new ErroNotFound("O ID " + id + " não existe!");
    }
}
