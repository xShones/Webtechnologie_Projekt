// Authentifizierte Fetch-Methode
async function authFetch(url, options = {}) {
    const token = localStorage.getItem('jwtToken'); // Token aus dem lokalen Speicher abrufen
    if (!token) {
        alert('Kein Token gefunden! Bitte anmelden.');
        window.location.href = '/auth/login';
        return;
    }

    // Header mit Token hinzufügen
    options.headers = {
        ...options.headers,
        'Authorization': `Bearer ${token}`,
    };
    return await fetch(url, options);
}

// Beispiel: API-Call
async function getTransactions() {
    const response = await authFetch('/transactions/all', { method: 'GET' });
    if (response.ok) {
        const transactions = await response.json();
        console.log('Transaktionen:', transactions); // Debug: Transaktionen anzeigen
    } else if (response.status === 401) {
        alert('Nicht autorisiert. Bitte erneut anmelden.');
        window.location.href = '/auth/login';
    } else {
        alert('Fehler beim Abrufen der Daten.');
    }
}
