# Referências

* http://api.jquery.com/jQuery.ajax/
* http://api.jquery.com/focusin/
* https://developer.mozilla.org/en-US/docs/DOM/Mozilla_event_reference/focusin
* http://api.jquery.com/focusout/
* https://developer.mozilla.org/en-US/docs/DOM/Mozilla_event_reference/focusout

--
# Ajax
Para usar Ajax em nossa aplicação, podemos começar tanto do lado servidor, quanto do lado cliente.  
Nesse tutorial, optei por começar pelo lado servidor.  
  
--
# O servlet
Novamente, não é via de regra, mas para esse caso, irei criar um novo *Servlet* para tratar a requisição feita pelo cliente.  
Para isso, basta clicar na raiz do **projeto**, **novo**, **servlet**  
Chamaremos o **Servlet** de **BuscaCidade** e colocaremos ele no **pacote** chamado **controle**.
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img17.png">](#)  
  
Clique em `Próximo`.  
Deixa selecionado a caixa `Adicionar informações ao descritor de implantação (web.xml)`.  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img18.png">](#)  
  
Clique em `Finalizar`.  
Agora vamos editar o método **doGet**, pois para usarmos o Ajax, iremos usar o método **GET**.  
Podemos retirar a linha que está lá.  
[<img src="https://raw.github.com/hugonomura/imagens-tutorial/master/img19.png">](#)  
  
Para realizarmos o processamento, devemos primeiro recuperar o dado sobre o estado que foi recebido, através do método `getParameter` pertencente a **request**.  
Como o parâmetro passado pela página sera `uf`, vamos recuperá usando a seguinte linha:  
  
    String estado = request.getParameter("uf");
  
Agora, nosso retorno será uma **string** também. Vamos inicializar ela com a string vazia.  
  
    String cidades = "";
  
Como iremos retornar a lista já para o usuário, em formato **html**, é legal falarmos qual será o charset do retorno.  
Para isso, basta usar o método `setCharacterEncoding`.  
  
    response.setCharacterEncoding("UTF-8");
  
Com o parâmetro já recebido e a nossa **string** de retorno já inicializada, temos que fazer o devido processamento agora, fazendo com o que o retorno sejam as cidades associadas ao estado.  
  
    if(estado.equals("SP")){
      cidades += "<option value=\"São Paulo\">São Paulo</option>";
      cidades += "<option value=\"São Roque\">São Roque</option>";
      cidades += "<option value=\"Sorocaba\">Sorocaba</option>";
    }
    if(estado.equals("RJ")){
      cidades += "<option value=\"Angra dos reis\">Angra dos Reis</option>";
      cidades += "<option value=\"Parati\">Parati</option>";
      cidades += "<option value=\"Rio de Janeiro\">Rio de Janeiro</option>";
    }
    if(estado.equals("SC")){
      cidades += "<option value=\"Bandeirante\">Bandeirante</option>";
      cidades += "<option value=\"Florianópolis\">Florianópolis</option>";
      cidades += "<option value=\"Princesa\">Princesa</option>";
    }
  
Com tudo já feito, devemos retornar a **string** para o cliente, como já faríamos para qualquer outro **servlet**.  
  
    PrintWriter writer = response.getWriter();
    writer.print(cidades);
    writer.close();
  
Feito isso, nosso **servlet** deverá ficar da seguinte forma:  
  
    String estado = request.getParameter("uf");
    String cidades = "";
    response.setCharacterEncoding("UTF-8");
    if(estado.equals("SP")){
      cidades += "<option value=\"São Paulo\">São Paulo</option>";
      cidades += "<option value=\"São Roque\">São Roque</option>";
      cidades += "<option value=\"Sorocaba\">Sorocaba</option>";
    }
    if(estado.equals("RJ")){
      cidades += "<option value=\"Angra dos reis\">Angra dos Reis</option>";
      cidades += "<option value=\"Parati\">Parati</option>";
      cidades += "<option value=\"Rio de Janeiro\">Rio de Janeiro</option>";
    }
    if(estado.equals("SC")){
      cidades += "<option value=\"Bandeirante\">Bandeirante</option>";
      cidades += "<option value=\"Florianópolis\">Florianópolis</option>";
      cidades += "<option value=\"Princesa\">Princesa</option>";
    }
    PrintWriter writer = response.getWriter();
    writer.print(cidades);
    writer.close();
  
# O cliente
--
Com nosso servidor já feito, agora nos resta fazer nosso cliente.  
Certifique-se dos **ids** do campo `Estado` e `Cidade`, pois recuperaremos eles através do jQuery.  
No meu caso, o campo `Estado` está da seguinte forma:  
  
            <select name="estado" id="estado">
                <option value="" selected></option>
                <option value="RJ">RJ</option>
                <option value="SP">SP</option>
                <option value="SC">SC</option>
            </select>
  
E o campo `Cidade` está da seguinte forma:  
  
            <select name="cidade" id="cidade">
            </select>
  
Note que o campo o `Estado` possui uma opção vazia, apenas para que nenhum estado esteja pré-selecionado quando o usuário desejar se cadastrar, enquanto o campo `Cidade` não possui nenhuma opção, pois o servidor irá retornar as opções de acordo com o `Estado` que estiver selecionado.  
  
Com isso certificado, basta alterar o nosso **script**.  
Após o seu **footer**, existe a tag **script** e dentro dela há:  
  
      $(document).ready(function(){
              // alguns métodos de validação do form
      }
  
Ao final dos métodos de validação, mas ainda dentro do `$(document).ready`, devem estar as chamadas do nosso Ajax, que deverá ser chamado sempre que um `Estado` for selecionado. Para isso, devemos chamar nosso **Ajax** quando nosso campo `Estado` perder o *foco*.  
  
        $('#estado').focusout(function(){
            // ajax
        }
  
Para usarmos Ajax no cliente, devemos lembrar sempre de:  
  
1. Qual método de envio das informações iremos usar?
2. Qual o caminho para o nosso Servlet?
3. Qual o formato dos dados iremos receber?
4. Quais dados serão enviados?
5. O quer fazer após o Servlet retornar as informações?
  
No nosso caso:  
  
1. Como alteramos o método `doGet` no **Servlet**, devemos enviar usando **GET**.
2. Como adicionamos no nosso arquivo `web.xml`, o caminho é, simplesmente, `BuscaCidade`.
3. Os dados que o **Servlet** retorna são `HTMl`.
4. O dado que será enviado é o valor do campo estado, sendo que o nome do parâmetro deve ser `uf`, como é esperado no **Servlet**.
5. Preencher a lista de cidades associadas ao estado.
  
Para realizar isso, basta fazermos o seguinte:  
  
          $.ajax({
            type: "GET",
            url: "BuscaCidade",
            dataType: "html",
            data: {uf: $('#estado').val() }
          }).done(function(data){
            $('#cidade').html(data);
          });
  
Explicando cada chave do *ajax*.  
`type` é o método que será usado para o envio das informações para o servidor;  
`url` é o caminho para o **Servlet** que irá responder a requisição;  
`dataType` é o tipo de dado que o **Servlet** irá retornar;  
`data` é um **json** com as chaves e valores que serão enviados para o servlet processar.  
O método `.done` é o que irá preencher a lista de cidades com o valor retornado pela requisição **Ajax**, sendo que **data** é o que o servidor retornou.  
