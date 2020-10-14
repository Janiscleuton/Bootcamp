package com.banco_digital.controle;

import com.banco_digital.dao.Headers;
import com.banco_digital.dao.SegundoPassoDao;
import com.banco_digital.erros.ErroNotFound;
import com.banco_digital.modelo.cliente.SegundoPasso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//Classe que controla o objeto do segundo passo
@RestController
@RequestMapping(path = "/segundo_passo")
public class SegundoPassoControle {
    public final SegundoPassoDao segundoPassoDao;

    @Autowired
    Headers headers;

    @Autowired
    public SegundoPassoControle(SegundoPassoDao segundoPassoDao) {
        this.segundoPassoDao = segundoPassoDao;
    }

    //Metodo para salvar o segundo passo
    @PostMapping
    public ResponseEntity<?> salvarSegundoPasso(@Valid @RequestBody @RequestHeader SegundoPasso segundoPasso) {
        segundoPassoDao.save(segundoPasso);
        return new ResponseEntity<>(headers.headerUri2().getHeaders(), HttpStatus.CREATED);
    }

    //Metodo para listar o segundo passo
    @GetMapping
    public ResponseEntity<?> listarSegundoPasso() {
        return new ResponseEntity<>(segundoPassoDao.findAll(), HttpStatus.OK);
    }

    //Metodo para buscar o segundo passo por Id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> buscarSegundoPasso(@PathVariable("id") Long id) {
        verificarSeEnderecoDoClienteExiste(id);
        SegundoPasso segundoPasso = segundoPassoDao.findById(id).get();
        return new ResponseEntity<>(segundoPasso, HttpStatus.OK);
    }

    //Metodo para atualizar o segundo passo
    @PutMapping
    public ResponseEntity<?> atualizarSegundoPasso(@RequestBody SegundoPasso segundoPasso) {
        verificarSeEnderecoDoClienteExiste(segundoPasso.getId());
        segundoPassoDao.save(segundoPasso);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Metodo para excluir o segundo passo por Id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> excluirSegundoPasso(@PathVariable Long id) {
        verificarSeEnderecoDoClienteExiste(id);
        segundoPassoDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Metodo para verificar se o segundo passo existe
    private void verificarSeEnderecoDoClienteExiste(Long id) {
        if (segundoPassoDao.findById(id).isEmpty())
            throw new ErroNotFound("O ID " + id + " não existe!");
    }
}
