# language: pt

  Funcionalidade: Praticando automação de testes preenchimento de formulario DemoQA

    Cenario: Preencher formulario e submenter
      Dado que eu esteja na pagina do formulario
      Quando eu preencho o primeiro nome "Felipe" e o sobrenome "Silva"
      E eu preencho o email "a@a.com.br"
      E eu preencho o genero "Male"
      E eu preencho o número de celular "11988887777"
      E eu preencho a data de nascimento "13 Nov 1989"
      E eu preencho o campo Subjects com "Maths"
      E seleciono o hobby "Sports"
      E eu envio o formulario
      Então eu devo ver o modal de configuração com o texto "Thanks for submitting the form"