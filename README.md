# Envio de Mensagem Via Bot Telegram

Este projeto consiste em uma aplicação web simples que permite enviar mensagens e imagens para o Telegram utilizando um bot. O usuário pode escolher entre enviar uma imagem por arquivo ou por URL.

## Funcionalidades

- **Envio de Mensagem:** Você pode enviar uma mensagem de texto para qualquer chat do Telegram especificando o `ChatID` e o `Token` do bot.
  
- **Envio de Imagem:** É possível enviar uma imagem junto com a mensagem, seja por upload de arquivo ou inserção da URL da imagem.

- **Tópico Opcional:** Existe a opção de especificar um `ID do Tópico` para agrupar mensagens relacionadas.

### Pré-requisitos

- Navegador web moderno (recomendado Chrome, Firefox, Safari)
- Token de acesso do seu bot do Telegram
- ChatID do grupo ou canal ou privado

### Preencha os campos necessários:

- ChatID: Informe o ID do chat para onde deseja enviar a mensagem.
- Token: Insira o token do seu bot do Telegram.
- Mensagem: Digite o conteúdo da mensagem que deseja enviar.
- Tipo de Imagem: Selecione entre Arquivo para fazer upload de uma imagem ou URL para fornecer uma URL de imagem.
- Foto (opcional): Se escolher Arquivo, selecione uma imagem localmente.
- URL da Imagem: Se escolher URL, forneça a URL da imagem.

Clique em Enviar para enviar a mensagem e, se necessário, a imagem para o Telegram.

Caso a mensagem seja enviada com sucesso, uma janela modal informará que a mensagem foi enviada com sucesso. Caso contrário, um alerta será mostrado com o erro ocorrido.

### Tecnologias Utilizadas

HTML
JavaScript (com jQuery para facilitar interações com o DOM)
Bootstrap 4 (para estilização básica e componentes responsivos)
Telegram Bot API (para enviar mensagens e imagens via bot)
