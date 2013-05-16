<!DOCTYPE html>
<html>
  <head>
    <title>Caronas | Inicio</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="estilo.css">
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  </head>
  <body>
    <header class="container">
      <h1 id="logo"><a href="index.html">Caronas</a></h1>
      <nav id="menu">
        <ul>
            <% 
                if(request.getParameter("admin")){
              %>
            <li><a href="#.html" class="active">Autorização de usuário</a></li>
            <li><a href="#.html">Cadastro de Rotas</a></li>
            <%
                }
              %>
        </ul>
      </nav>
    </header>
    <section class="container">
      <article id="form">
        <header>
          <h1>Login</h1>
        </header>
        <form method="post" action="EfetuaLogin">
          <p><label for="login">Login</label><input type="text" id="login" name="login"></p>
          <p><label for="senha">Senha</label><input type="password" id="senha" name="senha"></p>
          <p><input type="submit" value="Logar!"></p>
        </form>
      </article>
    </section>
    <footer class="container">
      <p>Desenvolvimento Web - UFSCar Sorocaba - 2013</p>
    </footer>
    <script>
      $(document).ready(function(){

      });
    </script>
  </body>
</html>
