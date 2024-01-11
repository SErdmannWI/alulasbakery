const url = 'http://localhost:8080/stations';

function getAllStations() {
    fetch(`${url}/getStation/all`)
        .then(response => response.json())
        .then(stations => {
        console.log('All Stations: ', stations)
    })
    .catch(error => console.error('Error getting stations', error));
}

function getStation() {
    fetch(`${url}/getStation/${stationId}`)
        .then(response => response.json())
        .then(station => {
            console.log('Station Details: ', station)
    })
    .catch(error => console.error('Error getting station', error));
}

function startAllStations() {
    fetch(`${url}/stationAction/startAll`, { method: 'PUT' })
        .then(response => response.json())
        .then(stations => console.log('All stations started', stations))
        .catch(error => console.error('Error starting stations', error));
}

function resumeAllStations() {
    fetch(`${url}/stationAction/resumeAll`, { method: 'PUT' })
        .then(response => response.json())
        .then(stations => console.log('All Stations resumed', stations))
        .catch(error => console.error('Error resuming stations', error));
}

function pauseAllStations() {
    fetch(`${url}/stationAction/pauseAll`, { method: 'PUT' })
        .then(response => response.json())
        .then(stations => console.log('All Stations paused', stations))
        .catch(error => console.error('Error pausing stations', error));
}

function stopAllStations() {
    fetch(`${url}/stationAction/stopAll`, { method: 'PUT' })
        .then(response => response.json())
        .then(stations => console.log('All Stations stopped', stations))
        .catch(error => console.error('Error stopping stations', stations));
}

function startIndividualStation(stationNumber) {
    fetch(`${url}/stationAction/startIndividual/${stationNumber}`, { method: 'PUT' })
        .then(response => response.json())
        .then(station => console.log(`Started station number ${stationNumber}`, station))
        .catch(error => console.error(`Error starting station number ${stationNumber}`, error));
}

function resumeIndividualStation(stationNumber) {
    fetch(`${url}/stationAction/resumeIndividual/${stationNumber}`, { method: 'PUT' })
        .then(response => response.json())
        .then(station => console.log(`Resumed station number ${stationNumber}`, station))
        .catch(error => console.error(`Error resuming station number ${stationNumber}`, error));
}

function pauseIndividualStation(stationNumber) {
    fetch(`${url}/stationAction/pauseIndividual/${stationNumber}`, { method: 'PUT' })
        .then(response => response.json())
        .then(station => console.log(`Paused station number ${stationNumber}`))
        .catch(error => console.error(`Error pausing station number ${stationNumber}`, error));
}

function stopIndividualStation(stationNumber) {
    fetch(`${url}/stationAction/stopIndividual/{id}`, { method: 'PUT' })
        .then(response => response.json())
        .then(station => console.log(`Stopped station number ${stationNumber}`, station))
        .catch(error => console.error(`Error stopping station number ${stationNumber}`, error));

    updateProductQueue();
}

/*------------------------------------------- Product Queue Endpoint Calls -------------------------------------------*/
async function addProductToQueue(product) {
    await fetch(`${url}/productQueue/addProduct/${product}`, { method: 'PUT' })
            .then(response => response.json())
            .then(productAdded => console.log('Product added:', productAdded))
            .catch(error => console.error('Error creating product'));

    updateProductQueue();
}

function getBackgroundColor(status) {
    switch (status) {
        case 1: return 'green';
        case 2: return 'yellow';
        case 3: return 'red';
        case 4: return 'blue';
        case 5: return 'purple';
        default: return 'none'; // Default case for status 0
    }
}

function updateProductQueue() {
    fetch(`${url}/productQueue/viewProductQueue`, { method: 'GET' })
        .then(response => response.json())
        .then(products => {
            const productQueueContainer = document.getElementById('product-queue-list')
            productQueueContainer.innerHTML = ''; //Clear previous content

            products.forEach(product => {
                let listItem = document.createElement('ul');
                listItem.className = "product-queue-item";
                listItem.innerHTML = `<h3>${product.productName}</h3>`;

                productQueueContainer.appendChild(listItem);
            });
        })
    .catch(error => console.error('Error generating Product Queue'));
}

function updateStationGrid() {
    fetch(`${url}/getStation/all`)
        .then(response => response.json())
        .then(stations => {
        const gridContainer = document.getElementById('stationGrid');
        gridContainer.innerHTML = ''; // Clear previous content

        stations.forEach(station => {
            const gridItem = document.createElement('div');
            gridItem.classList.add('grid-item');
            gridItem.style.backgroundColor = getBackgroundColor(station.stationStatus);
            let currentProduct = "No Product";
            if (station.currentProduct != null) {
                currentProduct = station.currentProduct.productName;
            }
            gridItem.innerHTML = `
                    <h3>${station.stationName}</h3>
                    <p>ID: ${station.stationId}</p>
                    <p>Status: ${station.stationStatus}</p>
                    <p>Current Product: ${currentProduct}</p>
                `;
            gridContainer.appendChild(gridItem);
        });
    })
        .catch(error => console.error('Error fetching stations:', error));
}

setInterval(updateStationGrid, 100);
setInterval(updateProductQueue, 100);

// Call updateStationGrid() to initially populate the grid
updateStationGrid();