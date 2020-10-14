package com.banco_digital.controle;

import com.banco_digital.dao.Headers;
import com.banco_digital.dao.PrimeiroPassoDao;
import com.banco_digital.erros.ErroNotFound;
import com.banco_digital.erros.TipoDeErro;
import com.banco_digital.modelo.cliente.PrimeiroPasso;
import com.banco_digital.utilitarios.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//Classe que controla o objeto do primeiro passo
@RestController
@RequestMapping("primeiro_passo")
public class PrimeiroPassoControle {
    private final PrimeiroPassoDao primeiroPassoDao;
    private int idade;

    DateUtil dateUtil;
    @Autowired
    Headers headers;

    @Autowired
    public PrimeiroPassoControle(PrimeiroPassoDao primeiroPassoDao) {
        this.primeiroPassoDao = primeiroPassoDao;
    }

    //Metodo para listar o primeiro passo
    @GetMapping
    public ResponseEntity<?> listarPrimeiroPasso() {
        return new ResponseEntity<>(primeiroPassoDao.findAll(), HttpStatus.OK);
    }

    //Metodo para salvar o primeiro passo
    @RequestMapping(method = RequestMethod.POST, value = "/salvar")
    public ResponseEntity<?> salvarPrimeiroPasso(@Valid @RequestBody @RequestHeader PrimeiroPasso primeiroPasso) {
        // chama o metodo calculaIdade da classe DateUtil enviando a data de nascimento capturada como parametro e devolve o resultado na variavel idade
        idade = DateUtil.calculaIdade(primeiroPasso.getDataNascimento());
        if (idade < 18) //verifica se a idade e menor que 18 anos
            return new ResponseEntity<>(new TipoDeErro("Só é permitido abrir conta maior de 18 anos!"),
                    HttpStatus.BAD_REQUEST); //se for menor que 18 anos retorna a menssagem de erro
        primeiroPassoDao.save(primeiroPasso);// se não for menor que 18 anos salva os dados e chama o proximo passo
        return new ResponseEntity<>(headers.headerUri().getHeaders(), HttpStatus.CREATED);
    }

    //Metodo para buscar o primeiro passo por Id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> buscarPrimeiroPasso(@PathVariable("id") Long id) {
        verificarSeClienteExiste(id);
        PrimeiroPasso primeiroPasso = primeiroPassoDao.findById(id).get();
        return new ResponseEntity<>(primeiroPasso, HttpStatus.OK);
    }

    //Metodo para buscar o primeiro passo por nome
    @GetMapping(path = "/Nome/{nome}")
    public ResponseEntity<?> buscarPrimeiroPasso(@PathVariable String nome) {
        return new ResponseEntity<>(primeiroPassoDao.findByNomeIgnoreCaseContaining(nome), HttpStatus.OK);
    }

    //Metodo para atualizar o primeiro passo
    @PutMapping
    public ResponseEntity<?> atualizarPrimeiroPasso(@RequestBody PrimeiroPasso primeiroPasso) {
        verificarSeClienteExiste(primeiroPasso.getId());
        primeiroPassoDao.save(primeiroPasso);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Metodo para excluir o primeiro passo por Id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> excluirPrimeiroPasso(@PathVariable Long id) {
        verificarSeClienteExiste(id);
        primeiroPassoDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Metodo para verificar se o primeiro passo existe
    private void verificarSeClienteExiste(Long id) {
        if (primeiroPassoDao.findById(id).isEmpty())
            throw new ErroNotFound("O ID " + id + " não existe!");//menssagem de erro
    }
}
