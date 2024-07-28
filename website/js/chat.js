function togglePhotoInput() {
    var photoOption = document.getElementById("photoOption").value;
    document.getElementById("photoUrlGroup").style.display = photoOption === "url" ? "block" : "none";
    document.getElementById("photoFileGroup").style.display = photoOption === "file" ? "block" : "none";
}

function sendMessage() {
    var chatId = document.getElementById("chatId").value;
    var token = document.getElementById("token").value;
    var message = document.getElementById("message").value;
    var photoOption = document.getElementById("photoOption").value;

    if (chatId.trim() === "" || token.trim() === "" || message.trim() === "") {
        alert("Por favor, preencha todos os campos obrigatórios!");
        return;
    }

    var formData = new FormData();
    formData.append("chat_id", chatId);

    var url;
    if (photoOption === "url") {
        var photoUrl = document.getElementById("photoUrl").value;
        if (photoUrl.trim() !== "") {
            formData.append("photo", photoUrl);
            formData.append("caption", message);
            url = "https://api.telegram.org/bot" + token + "/sendPhoto";
        } else {
            alert("Por favor, preencha a URL da foto!");
            return;
        }
    } else if (photoOption === "file") {
        var photoFile = document.getElementById("photoFile").files[0];
        if (photoFile) {
            formData.append("photo", photoFile);
            formData.append("caption", message);
            url = "https://api.telegram.org/bot" + token + "/sendPhoto";
        } else {
            alert("Por favor, selecione um arquivo de foto!");
            return;
        }
    } else {
        formData.append("text", message);
        url = "https://api.telegram.org/bot" + token + "/sendMessage";
    }

    var request = new XMLHttpRequest();
    request.open("POST", url, true);

    request.onreadystatechange = function() {
        if (request.readyState === 4) {
            if (request.status === 200) {
                $('#successModal').modal('show');
            } else {
                alert("Erro ao enviar mensagem: " + request.statusText);
            }
        }
    };

    request.send(formData);
}
