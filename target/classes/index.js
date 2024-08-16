
// ════════════════ Exercise 1 ════════════════════ //
/* 
TODO: Edit the string assigned to the variable 'result' so that it uses template literals rather than string concatenation to print out "Hey [FRIEND'S NAME]," where the text in [FRIEND'S NAME] is replaced by the value within the `name` parameter.

No other edits need to be made to the function.
*/
function exercise1(name) {

    let result = "Hey [FRIEND'S NAME],";

    document.getElementById("e1").innerText = result;

    return result;
}

// ════════════════ Exercise 2 ════════════════════ //
/* 
TODO: Edit the string assigned to the variable 'result' so that it uses template literals rather than string concatenation to print out the following:

"I just wanted to take a moment to tell you how [ADJECTIVE 1] you are!
Remember that time we [PAST TENSE VERB] together?
That was [ADJECTIVE 2]! We need to do it again soon."

- Make sure to maintain line breaks without using newline characters (\n). 
- The text in [ADJ1] should be replaced by the value within the `adj1` parameter.
- The text in [VERB] should be replaced by the value within the `verb` parameter.
- The text in [ADJ2] should be replaced by the value within the `adj2` parameter.
*/
function exercise2(adj1, verb, adj2) {

    
    let result = "I just wanted to take a moment to tell you how [ADJ1] you are!\n" +
    "Remember that time we [VERB] together?\n" +
    "That was [ADJ2]! We need to do it again soon.";

    document.getElementById("e2").innerText = result;

    return result;
}

// ════════════════ Exercise 3 ════════════════════ //
/* 
TODO: Edit the string assigned to the variable 'result' so that it uses a tagged template rather than string concatenation. The function to use is named 'sarcasmCase'. You do not need to edit the sarcasmCase or sarcasmConverter functions. The text in [ADJ] should be replaced by the value within the `adj` parameter.
*/
function exercise3(adj) {

    let result = "I hope you're having a [ADJ] day!"

    document.getElementById("e3").innerText = result;

    return result;
}

// function for tagged template
function sarcasmCase(strings, ...values) {

    let result = '';

    // figure out ending condition for our loop
    let maxIndex = strings.length > values.length ? strings.length : values.length;

    // iterate over both string pieces and expressions
    for (let i = 0; i < maxIndex; i++) {
        // if there is still a string to process,
        if (i < strings.length) {
            // process the string and concatenate converted string to result
            result += sarcasmConverter(strings[i]);
        }

        // if there is still an embedded expression to process,
        if (i < values.length) {
            // process the value and concatenate converted string to result
            result += sarcasmConverter(values[i]);
        }
    }

    return result;
}

// character checker helper function
function sarcasmConverter(val) {
    let result = '';


    // if the value is a number
    if (typeof val == 'number') {
        // directly add it to result
        result += val;
    } else {
        // check every character
        for (let i = 0; i < val.length; i++) {
            // and if its position is odd
            if (i % 2 != 0) {
                // convert it to uppercase and add it to the result
                result += val[i].toUpperCase();
            } else {
                // else convert it to lowercase and add it to the result
                result += val[i].toLowerCase();
            }
        }
    }

    return result;
}

// ════════════════════════════════════════════════ //
/* Note: You do not need to edit or view any code below this point. */

function handleSubmit(event) {
    // Prevent default form submission behavior
    event.preventDefault();

    // Retrieve form data
    const formData = new FormData(event.target);

    // Process form data
    exercise1(formData.get("friendName"));
    exercise2(formData.get("adjective1"), formData.get("pastVerb"), formData.get("adjective2"));
    exercise3(formData.get("adjective3"));

    // Reset the form
    event.target.reset();
}

// Get the form element, add event listener
document.getElementById("madLibForm")?.addEventListener("submit", handleSubmit);

// export functions for testing
if (typeof module === 'object'){
    module.exports = {exercise1, exercise2, exercise3};
}