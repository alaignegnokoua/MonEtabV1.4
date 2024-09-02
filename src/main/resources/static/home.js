var ctx = document.getElementById('genderChart').getContext('2d');
var genderChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ['Garçons', 'Filles'],
        datasets: [{
            label: 'Répartition des élèves par genre',
            data: [200, 150], // Données fictives pour les garçons et les filles
            backgroundColor: ['orange', 'green'],
            borderColor: ['orange', 'green'],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false
    }
});