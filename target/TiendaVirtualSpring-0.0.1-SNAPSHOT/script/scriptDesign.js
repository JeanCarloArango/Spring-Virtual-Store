const links = document.querySelectorAll("#menu a");

links.forEach((link) => {
    link.addEventListener('click', (e) => {
        e.preventDefault();
        links.forEach((link) => link.classList.remove('active'));
        e.target.classList.add('active');
    });
});