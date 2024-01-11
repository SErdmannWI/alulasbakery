function getAllTests() {
    fetch('/test/allTests')
        .then(response => response.json())
        .then(tests => {
        const testsList = document.getElementById('testsList');
        testsList.innerHTML = ''; // Clear the list
        tests.forEach(test => {
            let listItem = document.createElement('li');
            listItem.textContent = `ID: ${test.testId}, Name: ${test.testName}`;
            testsList.appendChild(listItem);
        });
    })
        .catch(error => console.error('Error fetching all tests:', error));
}

function getTestById() {
    const testId = document.getElementById('searchTestId').value;
    fetch(`/test/getTest/${testId}`)
            .then(response => {
                if(!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
            return response.json();
    })
        .then(test => {
        const resultElement = document.getElementById('singleTestResult');
        resultElement.textContent = `ID: ${test.testId}, Name: ${test.testName}`;
    })
        .catch(error => {
        console.error('Error fetching test by ID:', error);
        alert(error);
    });
}

function addNewTest() {
    const testId = document.getElementById('newTestId').value;
    const testName = document.getElementById('newTestName').value;

    fetch('/test/newTest', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ testId: testId, testName: testName })
    })
        .then(response => response.json())
        .then(test => {
        alert(`New test added with ID: ${test.testId}, Name: ${test.testName}`);
        // Clear the input fields
        document.getElementById('newTestId').value = '';
        document.getElementById('newTestName').value = '';
        // Optionally, refresh the list of all tests
        getAllTests();
    })
        .catch(error => {
        console.error('Error adding new test:', error);
        alert('Error adding test!');
    });
}
