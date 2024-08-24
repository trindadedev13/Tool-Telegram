document.addEventListener("DOMContentLoaded", function() {
    updateFooterYear();
});

function updateFooterYear() {
    const currentYear = new Date().getFullYear();
    document.getElementById("year").textContent = currentYear;
}

// jQuery for smooth scrolling on navbar click
$(document).ready(function(){
    $("nav a").on('click', function(event) {
        if (this.hash !== "") {
            event.preventDefault();
            var hash = this.hash;

            $('html, body').animate({
                scrollTop: $(hash).offset().top - 70
            }, 800);
        }
    });
});
