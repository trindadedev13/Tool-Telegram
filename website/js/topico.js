function openImageModal() {
    $('#imageModal').modal('show');
}

function saveImageSelection() {
    var imageType = document.querySelector('input[name="imageType"]:checked').value;
    if (imageType === "file") {
        var photo = document.getElementById("photo").files[0];
        if (!photo) {
            alert("Por favor, selecione um arquivo de imagem.");
            return;
        }
        // Salvando no localStorage (deixe vazio pois os arquivos não podem ser armazenados diretamente)
        localStorage.setItem("photoFile", "");
    } else if (imageType === "url") {
        var photoUrl = document.getElementById("photoUrl").value;
        if (photoUrl.trim() === "") {
            alert("Por favor, informe a URL da imagem.");
            return;
        }
        localStorage.setItem("photoUrl", photoUrl);
    }
    localStorage.setItem("imageType", imageType);
    $('#imageModal').modal('hide');
}

function sendMessage() {
    var chatId = document.getElementById("chatId").value;
    var token = document.getElementById("token").value;
    var message = document.getElementById("message").value;
    var imageType = localStorage.getItem("imageType");
    var formData = new FormData();

    if (chatId.trim() === "" || token.trim() === "" || message.trim() === "") {
        alert("Por favor, preencha todos os campos obrigatórios!");
        return;
    }

    formData.append("chat_id", chatId);
    formData.append("caption", message);

    if (imageType === "file") {
        var photo = document.getElementById("photo").files[0];
        if (!photo) {
            alert("Por favor, selecione um arquivo de imagem.");
            return;
        }
        formData.append("photo", photo);
        var url = "https://api.telegram.org/bot" + token + "/sendPhoto";
    } else if (imageType === "url") {
        var photoUrl = localStorage.getItem("photoUrl");
        formData.append("photo", photoUrl);
        var url = "https://api.telegram.org/bot" + token + "/sendPhoto";
    }

    var topicId = document.getElementById("topicId").value;
    if (topicId.trim() !== "") {
        formData.append("message_thread_id", topicId);
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

    // Salvando informações no localStorage
    localStorage.setItem("chatId", chatId);
    localStorage.setItem("token", token);
    localStorage.setItem("topicId", topicId);
}

document.addEventListener('DOMContentLoaded', function () {
    var imageTypeRadios = document.querySelectorAll('input[name="imageType"]');
    var photoFileGroup = document.getElementById("photoFileGroup");
    var photoUrlGroup = document.getElementById("photoUrlGroup");

    function toggleImageInput() {
        if (document.querySelector('input[name="imageType"]:checked').value === "file") {
            photoFileGroup.style.display = "block";
            photoUrlGroup.style.display = "none";
        } else {
            photoFileGroup.style.display = "none";
            photoUrlGroup.style.display = "block";
        }
    }

    imageTypeRadios.forEach(radio => {
        radio.addEventListener('change', toggleImageInput);
    });

    // Recuperando dados do localStorage
    var savedChatId = localStorage.getItem("chatId");
    var savedToken = localStorage.getItem("token");
    var savedTopicId = localStorage.getItem("topicId");
    var savedImageType = localStorage.getItem("imageType");
    var savedPhotoUrl = localStorage.getItem("photoUrl");

    if (savedChatId) {
        document.getElementById("chatId").value = savedChatId;
    }
    if (savedToken) {
        document.getElementById("token").value = savedToken;
    }
    if (savedTopicId) {
        document.getElementById("topicId").value = savedTopicId;
    }
    if (savedImageType) {
        document.querySelector(`input[name="imageType"][value="${savedImageType}"]`).checked = true;
        toggleImageInput();
        if (savedImageType === "url" && savedPhotoUrl) {
            document.getElementById("photoUrl").value = savedPhotoUrl;
        }
    }
});
