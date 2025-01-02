const reservationForm = document.getElementById('reservationForm');
const reservationList = document.getElementById('reservationList');
const apiUrl = 'http://localhost:8080/reservations'; // Upewnij się, że adres jest poprawny

// Funkcja do pobierania rezerwacji
async function fetchReservations() {
    const response = await fetch(apiUrl);
    if (!response.ok) {
        console.error('Błąd podczas pobierania rezerwacji:', response.statusText);
        return;
    }
    const reservations = await response.json();
    displayReservations(reservations);
}

// Funkcja do wyświetlania rezerwacji
function displayReservations(reservations) {
    reservationList.innerHTML = '';
    reservations.forEach(reservation => {
        const li = document.createElement('li');
        li.style.display = 'flex'; // Użyj flexboxa
        li.style.justifyContent = 'space-between'; // Rozmieść elementy
        li.style.alignItems = 'center'; // Wyrównaj elementy w pionie
        li.style.width = '100%'; // Ustal szerokość
        li.style.maxWidth = '600px'; // Ustal maksymalną szerokość
        li.style.margin = '10px 0'; // Odstęp między elementami

        li.innerHTML = `
            <span>${reservation.customerName} - ${reservation.date} - ${reservation.numberOfPeople} osób</span>
        `;
        li.appendChild(createDeleteButton(reservation.id));
        reservationList.appendChild(li);
    });
}

// Funkcja do tworzenia przycisku usuwania
function createDeleteButton(id) {
    const button = document.createElement('button');
    button.textContent = 'Usuń';
    button.onclick = () => deleteReservation(id);
    button.classList.add('delete-button'); // Dodaj klasę CSS
    return button;
}

// Funkcja do dodawania rezerwacji
reservationForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const customerName = document.getElementById('customerName').value;
    const date = document.getElementById('date').value; // Użyj wartości z inputu
    const numberOfPeople = document.getElementById('numberOfPeople').value;

    const newReservation = {
        customerName,
        date,
        numberOfPeople
    };

    const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newReservation),
    });

    if (!response.ok) {
        const errorMessage = await response.text();
        console.error('Błąd podczas dodawania rezerwacji:', errorMessage);
    } else {
        reservationForm.reset();
        fetchReservations(); // Odśwież listę rezerwacji
    }
});

// Funkcja do usuwania rezerwacji
async function deleteReservation(id) {
    const response = await fetch(`${apiUrl}/${id}`, {
        method: 'DELETE',
    });
    if (response.ok) {
        fetchReservations(); // Odśwież listę rezerwacji
    } else {
        console.error('Błąd podczas usuwania rezerwacji:', response.statusText);
    }
}

// Inicjalizacja
fetchReservations();