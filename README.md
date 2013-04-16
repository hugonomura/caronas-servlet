# Criando o projeto...
--
Para criar o projeto, basta clicar Arquivo > Novo projeto (ou simplesmente `Ctrl` + `Shift` + `N`)  
Selecionar **Aplicação Web** dentro de **Java Web**  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img6.png">](#)  
  
Clique em **Próximo**.  
Definir um nome para o projeto como, por exemplo, 'Caronas'.  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img7.png">](#)  
  
Clique em **Próximo**.  
Selecione o servidor **Apache Tomcat**.  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img8.png">](#)  
  
Clique em **Finalizar**.  
  
# Adicionando os HTML's
--
Como já possuímos as páginas do projeto prontas, podemos copiá-las dentro do nosso projeto. Podemos **copiar** e **colar** via **sistema operacional** mesmo, ou podemos fazê-lo através do próprio **Netbeans**, da seguinte forma:  
Com o projeto onde se encontram os HTML's abertos, selecione os arquivos necessários...  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img9.png">](#)  
  
e cole-os dentro do nosso projeto.  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img10.png">](#)  
  
Como já temos um **index.html**, podemos excluir o arquivo **index.jsp**, novamente, podemos fazê-lo via **sistema operacional** ou via **Netbeans** mesmo.  
  
# Editando o formulário...
--
Antes de implementarmos nosso **Servlet**, precisamos editar algumas coisas dentro do nosso `form`.
Como não queremos que a senha fique visível, iremos usar o método **post** e nossa **action** será o nome do nosso **Servlet**, nesse caso, iremos chamá-lo de **EfetuaLogin**.  
Para que possamos recuperar os campos do lado do servidor, também devemos dar **nomes** aos nosso campos.  
Nosso formulário vai ficar da seguinte forma:  
  
    <form method="get" action="EfetuaLogin">
      ... <input type="text" id="login" name="login"> ...
      ... <input type="password" id="senha" name="senha"> ...
      ...
    </form>
  
# Criando o Servlet
--
Para criar um **Servlet** dentro de nosso projeto, devemos clicar com o botão direito sobre o nome do nosso projeto...  
Selecionar `Novo` > `Servlet`.  
Temos que definir o nome da classe com mesmo nome da action do HTML, no meu caso, EfetuaLogin...  
Colocando o **Servlet** dentro do pacote **controle**.  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img11.png">](#)  
  
Clique em **Próximo**.  
Deixa habilitada a opção `Adicionar informações ao descritor de implantação (web.xml)`  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img11.png">](#)  
  
Clique em **Finalizar**.  
  
# Programando o Servlet
--
Toda a implementação do nosso **Servlet** será feita dentro do método `doGet`, uma vez que, estamos usando o método `get` no nosso formulário.  
Podemos apagar a linha que está lá.  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img15.png">](#)  
  
A validação que faremos será, caso os campos de **login** e **senha** possuam o mesmo valor e não sejam vazios, o login terá sido efetuado com sucesso, caso contrário, o login não será executado.  
Para isso, devemos primeiro recuperar os campos login e senha, através do método `getParameter` do objeto `HttpServletRequest`.  
Fazemos isso da seguinte forma:  
  
    String username = request.getParameter("login");
    String senha = request.getParameter("senha");
  
Com isso, já conseguimos recuperar os dados de **login** e **senha** enviados pelo **cliente**.  
Como a resposta enviada ao nosso cliente é uma **String** contendo a página, seria melhor invocar um método que constrói a nossa página de resposta, sendo que se o método receber um **nome de usuário** em branco, ele retornará a página de login com uma mensagem de erro ao efetuar o login, caso contrário, ele mostrará a mensagem "Ola `nome do usuário`!".  
Assim, a implementação do método ficaria:  
  
    private String constroiPagina(String usuario){
      String pagina;
      if (usuario.isEmpty()) {
        pagina = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "  <head>\n"
                + "    <title>Caronas | Inicio</title>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">\n"
                + "    <script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <header class=\"container\">\n"
                + "      <h1 id=\"logo\"><a href=\"index.html\">Caronas</a></h1>\n"
                + "      <nav id=\"menu\">\n"
                + "        <ul>\n"
                + "          <li><a href=\"index.html\" class=\"active\">Inicio</a></li>\n"
                + "          <li><a href=\"cadastro.html\">Cadastro</a></li>\n"
                + "        </ul>\n"
                + "      </nav>\n"
                + "    </header>\n"
                + "    <section class=\"container\">\n"
                + "      <article id=\"form\">\n"
                + "        <header>\n"
                + "          <h1>Login</h1>\n"
                + "        </header>\n"
                + "        <form method=\"get\" action=\"EfetuaLogin\">\n"
                + "          <p><label for=\"login\">Login</label><input type=\"text\" id=\"login\" name=\"login\"></p>\n"
                + "          <p><label for=\"senha\">Senha</label><input type=\"password\" id=\"senha\" name=\"senha\"></p>\n"
                + "          <p style=\"font-weight: bold;\">Login / Senha incorretos</p>\n"
                + "          <p><input type=\"submit\" value=\"Logar!\"></p>\n"
                + "        </form>\n"
                + "      </article>\n"
                + "    </section>\n"
                + "    <footer class=\"container\">\n"
                + "      <p>Desenvolvimento Web - UFSCar Sorocaba - 2013</p>\n"
                + "    </footer>\n"
                + "    <script>\n"
                + "      $(document).ready(function(){\n"
                + "\n"
                + "      });\n"
                + "    </script>\n"
                + "  </body>\n"
                + "</html>\n";
      } else {
        pagina = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "  <head>\n"
                + "    <title>Caronas | Ola " + usuario + "!</title>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">\n"
                + "    <script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <header class=\"container\">\n"
                + "      <h1 id=\"logo\"><a href=\"index.html\">Caronas</a></h1>\n"
                + "      <nav id=\"menu\">\n"
                + "        <ul>\n"
                + "          <li><a href=\"index.html\" class=\"active\">Inicio</a></li>\n"
                + "          <li><a href=\"#.html\">Perfil</a></li>\n"
                + "        </ul>\n"
                + "      </nav>\n"
                + "    </header>\n"
                + "    <section class=\"container\">\n"
                + "      <article id=\"form\">\n"
                + "        <header>\n"
                + "          <h1>Ola " + usuario + "!</h1>\n"
                + "        </header>\n"
                + "      </article>\n"
                + "    </section>\n"
                + "    <footer class=\"container\">\n"
                + "      <p>Desenvolvimento Web - UFSCar Sorocaba - 2013</p>\n"
                + "    </footer>\n"
                + "    <script>\n"
                + "      $(document).ready(function(){\n"
                + "\n"
                + "      });\n"
                + "    </script>\n"
                + "  </body>\n"
                + "</html>\n";
      }

      return pagina;
    }
  
Basicamente, eu apenas declarei a variável pagina, abri as aspas e colei meu conteúdo dentro delas (para arrumar a indentação eu simplesmente usei selecionei o texto e pressionei `Alt` + `Shift` + `F`, também poderia clicar com o botão direito e selecionar `formatar`).  
  
Com nosso método 'auxiliar' já implementado, podemos voltar para a implementação do método `doGet`.  
Devemos fazer a verificação se **username** é igual ou diferente de **senha**, sendo que eles forem iguais e **username** não for vazio, devemos chamar o método `constroiPagina` passando como parâmetro **username** e, caso contrário, chamar o método `constroiPagina` passando como parâmetro uma **String** vazia.  
Antes de gerarmos a página, também precisamos de um `PrintWriter`, para que possamos gerar a página de retorno para o usuário.  
Assim, devemos adicionar ao `doGet`:  
  
    PrintWriter retorno = response.getWriter();
    if(username.equals(senha) && !username.isEmpty()){
      // login efetuado com sucesso
      retorno.print(this.constroiPagina(username));
    }else{
      // falha ao executar o login
      retorno.print(this.constroiPagina(""));
    }
    retorno.close();
  
Nosso método `doGet` ficou da seguinte forma:  
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String username = request.getParameter("login");
      String senha = request.getParameter("senha");
      PrintWriter retorno = response.getWriter();
      if(username.equals(senha) && !username.isEmpty()){
        // login efetuado com sucesso
        retorno.print(this.constroiPagina(username));
      }else{
        // falha ao executar o login
        retorno.print(this.constroiPagina(""));
      }
      retorno.close();
    }
  
# Resultado esperado
--
O resultado esperado após a implementação de tudo é:  
  
* Caso algum dos campos esteja vazio ou `Login` e `Senha` forem diferentes:  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img13.png">](#)  
  
* Caso `Login` e `Senha` tiverem o mesmo valor e forem diferentes de vazio:  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img14.png">](#)  
  
--
  
Note que, ao enviarmos a senha, não existe nenhum tipo de tratamento.  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img16.png">](#)  
Que tal mudar o método de envio do formulário para `POST`? Lembre-se que, devemos editar o método `doPost` também.  
