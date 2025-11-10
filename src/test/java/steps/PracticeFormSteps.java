package steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.* ;
import org.junit.jupiter.api.Assertions;
import page.PracticeFormPage;

public class PracticeFormSteps {

    private PracticeFormPage page;

    @Dado("que eu esteja na pagina do formulario")
    public void abrirFormulario() {
        page = new PracticeFormPage();
        page.open();
    }

    @Quando("eu preencho o primeiro nome {string} e o sobrenome {string}")
    public void preencherNomeSobrenome(String first, String last) {
        page.setFirstName(first);
        page.setLastName(last);
    }

    @Quando("eu preencho o email {string}")
    public void preenchoEmail(String email) {
        page.setEmail(email);
    }

    @Quando("eu preencho o genero {string}")
    public void preenchoGenero(String gender) {
        page.setGender(gender);
    }

    @Quando("eu preencho o número de celular {string}")
    public void preenchoNumeroCelular(String mobile) {
        page.setMobile(mobile);
    }

    @Quando("eu preencho a data de nascimento {string}")
    public void preenchoDataNascimento(String date) {
        page.setDateofBirth(date);
    }

    @Quando("eu preencho o campo Subjects com {string}")
    public void preenchoSubjects(String subj) {
        page.setSubject(subj);
    }

    @Quando("seleciono o hobby {string}")
    public void selecionoHobby(String hobby) {
        page.selectHobby(hobby);
    }

    @Quando("eu envio o formulario")
    public void envioFormulario() {
        page.submit();
    }

    @Então("eu devo ver o modal de configuração com o texto {string}")
    public void verificarModal(String expectedText) {
        String modal = page.getModalText();
        System.out.println("Debug - Texto do modal capturado" + modal);

        Assertions.assertTrue(modal.contains(expectedText), "Modal não contém o texto esperado" + modal);

    }
    @After
    public void afterScenario(){

    }
}
