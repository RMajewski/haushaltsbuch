function toogleDisplayId(suite, test) {
	if (window.document.getElementById("id_" + suite + "_" + test).className ==
			"testoutInvisible")
		window.document.getElementById("id_" + suite + "_" + test).className = 
			"testoutVisible";
	else
		window.document.getElementById("id_" + suite + "_" + test).className =
			"testoutInvisible";
}
