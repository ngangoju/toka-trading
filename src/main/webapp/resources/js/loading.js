function showProgress(data) {
	var inputElement = data.source; // The HTML DOM input element.
	var ajaxStatus = data.status; // Can be "begin", "success" and "complete"

	var messageForInputElement = document.getElementById("loadingImage");

	switch (ajaxStatus) {
	case "begin": // This is called right before ajax request is been sent.
		
		openModal();
		break;

	case "complete": // This is called right after ajax response is received.
	
		closModal() ;
		break;

	case "success": // This is called when ajax response is successfully processed.
		if (messageForInputElement.innerHTML.length == 0) { // So, no message has been set.
			
			closModal() ;
		}
		break;
	}
}



//When the user clicks the button, open the modal 
function openModal() {
	//modal anaimation
	var modal = document.getElementById('myModal');

	//Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	//Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
 modal.style.display = "block";
}

//When the user clicks on <span> (x), close the modal
function closModal() {
	//modal anaimation
	var modal = document.getElementById('myModal');

	//Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	//Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
 modal.style.display = "none";
}

//When the user clicks anywhere outside of the modal, close it
/*window.onclick = function(event) {
	//modal anaimation
	var modal = document.getElementById('myModal');

	//Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	//Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
 if (event.target == modal) {
     modal.style.display = "none";
 }
}*/
