Sistema para alerta de novos chamados com integracao com a API do sistema GLPI

Esse e um sistema de alerta de novos chamados no sistema GLPI.

Para colocar para rodar, voce precisa apenas configurar a API do seu GLPI.

No arquivos "config.properties" temos os campos.

+ server.urlAPI = Url Geral do seu glpi Ex.http://servidos/glpi/
+ server.userToken = User Token, entre no usuario e no final da pagina pegue o token "API token"
+ server.appToken = Token no cliente API que voce deve configurar no GLPI
+ update.tempoUpdate = 1 *tempo em minutos*
+ position.altura = 55 *distancia da parte superior da tela.*

Obs. Lembre-se de colocar a faixa se IP autorizada no cliente.

Todos esses itens voce pode olhar na documentacao do proprio GLPI para ver como pegar essas informacoes.


![Capturar](https://user-images.githubusercontent.com/77954907/176536217-c6b6ef72-1759-4ea6-aaef-7c9fbc3cd3d6.PNG)

[Download - MediaFire (compilado)](https://www.mediafire.com/file/y7jnrm57yoghdal/PopUp_GLPI.rar/file)