/*
 * INSTRUCTIONS
 * From the root folder
 * >> node test/sample.js "Sam Person"
 * Nightmare window will pop up and run the test
 */

const Nightmare = require('nightmare');
const nightmare = Nightmare({show: true});
const URL = 'http://localhost:8000';

var value = process.argv[2];
testInputBox(value);

function testInputBox(value) {
	nightmare
		.goto(URL)
		.type('#input', value)
		.click('#button')
		.wait('#result')
		.wait(1000)
		.evaluate(function() {
			return document.querySelector('#result').innerText;
		})
		.end()
		.then(function(result) {
			if (value && result === value) {
				console.log('Success!', result, value);
			} else {
				console.log('Failure:', result, value);
			}
		})
		.catch(function(error) {
			console.error('Failure:', error);
		});
}