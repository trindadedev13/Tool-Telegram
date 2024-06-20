 // Inicialização do Slick Carousel
        $(document).ready(function(){
            $('.slider').slick({
                autoplay: true,
                autoplaySpeed: 3000,
                arrows: true,
                dots: true,
                infinite: true,
                speed: 500,
                slidesToShow: 1,
                slidesToScroll: 1
            });
        });
		
	document.addEventListener("DOMContentLoaded", function() {
    updateFooterYear();
});

function updateFooterYear() {
    const currentYear = new Date().getFullYear();
    document.getElementById("year").textContent = currentYear;
}