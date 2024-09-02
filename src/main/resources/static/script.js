// Fonction pour ouvrir le modal
document.querySelectorAll('.btn-modal').forEach(button => {
    button.addEventListener('click', function() {
        const modalId = this.getAttribute('data-target');
        // Vérifie si le modalId est bien formaté
        if (modalId && document.querySelector(modalId)) {
            const modal = document.querySelector(modalId);
            modal.style.display = "block";
        } else {
            console.error('Modal ID is not valid:', modalId);
        }
    });
});

// Fonction pour fermer le modal
document.querySelectorAll('.close').forEach(closeBtn => {
    closeBtn.addEventListener('click', function() {
        const modalId = this.getAttribute('data-target');
        // Vérifie si le modalId est bien formaté
        if (modalId && document.querySelector(modalId)) {
            const modal = document.querySelector(modalId);
            modal.style.display = "none";
        }
    });
});

// Fermer le modal lorsque l'utilisateur clique en dehors du modal
window.addEventListener('click', function(event) {
    document.querySelectorAll('.modal').forEach(modal => {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});
