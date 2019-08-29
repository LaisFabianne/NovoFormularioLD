
package br.edu.ifal.novoprojeto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class MeuControlador {

    @Autowired
    AlunoRepositorio repo;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView resposta = new ModelAndView("index.html");
        return resposta;
    }

    @RequestMapping("/formulario")
    public ModelAndView cadastro() {
        return new ModelAndView("form.html");
    }

    @RequestMapping(value = "/cadastro_aluno")
    public ModelAndView cadastroAluno(Aluno aluno) {
        ModelAndView resposta = new ModelAndView("form.html");
        // ...validar o que o usuário e salvar no BD
        repo.save(aluno);

        resposta.addObject("mensagem", "Cadastro com sucesso!");
        return resposta;
    }

    @RequestMapping("/lista_aluno")
    public ModelAndView listarAlunos() {
        ModelAndView retorno = new ModelAndView("lista_aluno.html");
        Iterable<Aluno> alunos = repo.findAll();
        retorno.addObject("alunos", alunos);
        return retorno;
    }

    @RequestMapping("/excluir_aluno/{idAluno}")
    public ModelAndView excluirAlunos(@PathVariable("idAluno") Long alunoID, RedirectAttributes redirect) {
        Optional<Aluno> opcao = repo.findById(alunoID);
        ModelAndView retorno = new ModelAndView("redirect:/lista_aluno");
        if (opcao.isPresent()) {
            Aluno a = opcao.get();
            repo.deleteById(a.getId());
            redirect.addFlashAttribute("mensagem", a.getNome() + " excluído com sucesso.");
            return retorno;
        } else {
            redirect.addFlashAttribute("mensagem", "Aluno " + alunoID + " não existe no Banco de Dados.");
            return retorno;
        }

    }

    @RequestMapping("/atualizar_aluno/{idAluno}")
    public ModelAndView atualizar(@PathVariable("idAluno") Long alunoID) {
        Optional<Aluno> opcao = repo.findById(alunoID);
        ModelAndView retorno = new ModelAndView("form.html");
        if (opcao.isPresent()) {
            Aluno a = opcao.get();
            retorno.addObject("aluno", a);
            return retorno;
        }
        return retorno;
    }

    @RequestMapping("")

}
