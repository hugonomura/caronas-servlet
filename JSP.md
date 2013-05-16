# Caronas
--
Continuando nosso projeto de caronas, agora iremos usar **JSP**.  
Iremos mexer no método **doPost** do *controller* `EfetuaLogin`, e caso o usuário seja `adm`, irão ser abertas novas opções no menu.  
  
	# doPost
	--
	Dentro do método **doPost**, remova as linhas de código que estiverem nela, deixando apenas a declaração do método.  
	[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img20.png">](#)  
	  
		Vamos fazer a recuperação dos dados do formulário normalmente no **servlet**, da seguinte forma:  
		  
			      String login = request.getParameter("login");
						      String senha = request.getParameter("senha");
									  
										Podemos fazer também algum tipo de validação de **login** e **senha**, como ainda não temos acesso ao banco de dados, podemos verificar simplesmente se **login** e **senha** tem o mesmo valor, da seguinte forma:  
										  
											      if(login.equals(senha)){
														         // aqui faremos o redirecionamento para a página 'logado'
																		       }else{
																					          // aqui faremos o redirecionamento para a página de login
																										      }
																													  
																														Agora, por uma questão de comodidade, faremos o redirecionamento de volta para a página de **login**, caso os campos `login` e `senha` não possuam o mesmo valor. Para isso, usaremos o método `sendRedirect`, que pertence ao objeto **response**.  
																														Para isso, basta fazermos isso:  
																														  
																															      response.sendRedirect("/index.html");
																																		  
																																			Com isso feito, voltemos para o nosso `if(login.equals(senha))` para realizar o redirecionamento para a página logado.  
																																			Para fazermos isso, precisamos do método `foward` pertencente a classe **RequestDispatcher**. Basta criarmos uma instância da classe **RequestDispatcher**, da seguinte forma:  
																																			  
																																				      RequestDispatcher rd = null;
																																							  
																																								Provavelmente, o **Netbeans** está indicando um erro nessa linha, pois não realizamos o import dessa classe. Resolvemos isso criando o import para **javax.servlet.RequestDispatcher** (basta adicionar `import javax.servlet.RequestDispatcher;` junto aos imports no inicio da classe)...  
																																								ou podemos simplesmente clicar sobre o ícone apontando o erro na linha...  
																																								[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img21.png">](#)  
																																								  
																																									 e pedir para a **IDE** realizar o import.  
																																									 [<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img22.png">](#)  
																																									   
																																										 Podemos adicionar um novo atributo ao objeto `request`, que chamaremos de **admin** que caso ela seja **true**, significa que o **usuário** é um **admin** e, caso contrário, o **usuário** é um usuário comum do sistema, para isso basta fazermos isso:  
																																										   
																																											         request.setAttribute("admin", true);
																																															   
																																																 Feito isso, já temos tudo que precisamos para fazermos o devido tratamento na **view**, nos restando apenas redirecionar a requisição para a **view** apropriada. Para isso basta usarmos o **RequestDispatcher**, da seguinte forma:  
																																																   
																																																	          rd = request.getRequestDispatcher("/viewLogado.jsp");
																																																						         rd.forward(request, response);
																																																										   
																																																											 Assim, a nossa página `viewLogado` irá tratar a requisição.  
																																																											   
																																																												 # Criando a view...
																																																												 --
																																																												 Para criar uma nova página **JSP**, basta clicarmos com o **botão direito** em `Páginas Web` > `Novo` > `JSP...`  
																																																												   
																																																													 Mudar o nome do arquivo para viewLogado...
																																																													 [<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img23.png">](#)  
																																																													   
																																																														 E clicar em `Finalizar`.  
																																																														   
																																																															 # Editando a view...
																																																															 --
																																																															 Agora que já temos a **view** criada, podemos editá-la de acordo com o que realmente queremos fazer.  
																																																															 Podemos apagar todo o conteúdo do arquivo gerado e substituir pelo conteúdo do nosso `index.html`, apenas para termos um ponto de partida.  
																																																															 Como queremos editar o menu superior, devemos encontrar as linhas do menu e alterá-las para aparecer diferentes opções caso o usuário seja do tipo **admin**, conforme o **atributo** que registramos no nosso **servlet**.  
																																																															 [<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img24.png">](#)  
																																																															   
																																																																 [<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img25.png">](#)  
																																																																   
																																																																	 # Resultado
																																																																	 --
																																																																	 Ao fim desse tutorial, o método `doPost` do **servlet** `EfetuaLogin`, deve estar assim:  
																																																																	   
																																																																		         @Override
																																																																						         protected void doPost(HttpServletRequest request, HttpServletResponse response)
																																																																										                 throws ServletException, IOException {
																																																																																		             String login = request.getParameter("login");
																																																																																								             String senha = request.getParameter("senha");

																																																																																														             if(login.equals(senha)){
																																																																																																				                   if(login.equals("adm")){
																																																																																																													                       RequestDispatcher rd = null;
																																																																																																																								                       request.setAttribute("admin", true);
																																																																																																																																			                       rd = request.getRequestDispatcher("/viewLogado.jsp");
																																																																																																																																														                       rd.forward(request, response);
																																																																																																																																																									                   }
																																																																																																																																																																		             }else{
																																																																																																																																																																								                 response.sendRedirect("/index.html");
																																																																																																																																																																																             }

																																																																																																																																																																																						         }
																																																																																																																																																																																										   
																																																																																																																																																																																											   
																																																																																																																																																																																												 E a página `viewLogado`, deve estar assim:  
																																																																																																																																																																																												   
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
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							   
