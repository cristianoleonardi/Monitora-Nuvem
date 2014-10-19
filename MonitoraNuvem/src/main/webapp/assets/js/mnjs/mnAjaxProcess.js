// Commit Guess - Determines save or update

function commitGuess(gssId, gssProId, gssUsrId, gssGameId, gssScoreTeamA, gssScoreTeamB, gssStatus) {
	if(gssId) {
            updateGuess(gssId, gssUsrId, gssGameId, gssScoreTeamA, gssScoreTeamB, gssStatus);
        } else if(gssProId) {
            updateGuess(gssProId, gssUsrId, gssGameId, gssScoreTeamA, gssScoreTeamB, gssStatus);
        } else {
            saveGuess(gssUsrId, gssGameId, gssScoreTeamA, gssScoreTeamB, gssStatus);
        }
}

//Save Guess

function saveGuess(gssUsrId, gssGameId, gssScoreTeamA, gssScoreTeamB, gssStatus) {
	if(gssUsrId && gssScoreTeamA && gssScoreTeamB) {
		var url = "guess/salvar/" + gssUsrId + "/" + gssGameId + "/" + gssScoreTeamA + "/" + gssScoreTeamB + "/" + gssStatus;
                document.getElementById("guessok" + gssGameId).innerHTML="<img src=\"http://localhost/sistemacopa/public_html/static/img/patterns/ajax_load.gif\">";
                requestHTTP("GET", url, true);
	} else {
            document.getElementById("feedbackguess" + gssGameId).innerHTML="<i class = \"icon-remove\"></i> Indique um palpite para cada time.";
            document.getElementById("feedbackguess" + gssGameId).className="error-guess";
        }
}

//Update Guess

function updateGuess(gssId, gssUsrId, gssGameId, gssScoreTeamA, gssScoreTeamB, gssStatus) {
	if(gssId, gssUsrId && gssScoreTeamA && gssScoreTeamB) {
		var url = "guess/atualizar/" + gssId + "/" + gssUsrId + "/" + gssGameId + "/" + gssScoreTeamA + "/" + gssScoreTeamB + "/" + gssStatus;
                requestHTTP("GET", url, true);
	} else {
            document.getElementById("feedbackguess" + gssGameId).innerHTML="<i class = \"icon-remove\"></i> Indique um palpite para cada time.";
            document.getElementById("feedbackguess" + gssGameId).className="error-guess";
        }
}

//Response

function processData() {
    //Start Scores With Zero
    var gameScoreA = 0;
    var gameScoreB = 0;
    
    //Make object with response JSON
    var retJSON = JSON.parse(ajax.responseText);
    
    //Define Action from JSON
    var actionExecute = retJSON.action;
    
    //Set vars from JSON
    var guessId = retJSON.guessid;
    var gameId = retJSON.gameid;
    if (retJSON.gamescorea) gameScoreA = retJSON.gamescorea;
    if (retJSON.gamescoreb) gameScoreB = retJSON.gamescoreb;
    
    //Set var JSON MSG
    var guessMsg = retJSON.msg;
    
    //Make Id's
    var idA = "scorea" + gameId;
    var idB = "scoreb" + gameId;
    var guessOk = "guessok" + gameId;
    var guessBottom = "guessbottom" + gameId;
    var awaitingGuess = "awaitingguess" + gameId;
    var awaitingGuessHeader = "awaitingguessheader" + gameId;
    var feedbackGuess = "feedbackguess" + gameId;
    var provisionalGuessId = "guessproid" + gameId;
    
    //Send return to view if saveGuessOk
    if(actionExecute === "saveGuessOk") {
        
        document.getElementById(guessOk).innerHTML="<i class = \"icon-thumbs-up icon-large\"></i>";
        document.getElementById(idA).value=gameScoreA;
        document.getElementById(idB).value=gameScoreB;
        document.getElementById(guessBottom).innerHTML="<i class=\"icon-pencil\"></i> Atualizar";
        document.getElementById(guessBottom).title="Atualizar palpite";
        document.getElementById(provisionalGuessId).value=guessId;
        document.getElementById(awaitingGuess).innerHTML="";
        document.getElementById(awaitingGuessHeader).className="content-box-header okguessheader";
        document.getElementById(feedbackGuess).innerHTML="<i class = \"icon-check\"></i> " + guessMsg;
        document.getElementById(feedbackGuess).className="success-guess";
        
    }
    
    //Send return to view if saveGuessOk
    if(actionExecute === "updateGuessOk") {
        
        document.getElementById(guessOk).innerHTML="<i class = \"icon-thumbs-up icon-large\"></i>";
        document.getElementById(idA).value=gameScoreA;
        document.getElementById(idB).value=gameScoreB;
        document.getElementById(guessBottom).innerHTML="<i class=\"icon-pencil\"></i> Atualizar";
        document.getElementById(guessBottom).title="Atualizar palpite";
        document.getElementById(awaitingGuess).innerHTML="";
        document.getElementById(awaitingGuessHeader).className="content-box-header okguessheader";
        document.getElementById(feedbackGuess).innerHTML="<i class = \"icon-check\"></i> " + guessMsg;
        document.getElementById(feedbackGuess).className="success-guess";
        
    }
    
    //Send return to view if saveGuessError
    else if (actionExecute === "saveGuessError")
    {
        
        document.getElementById(guessOk).innerHTML="";
        document.getElementById(idA).value=gameScoreA;
        document.getElementById(idB).value=gameScoreB;
        document.getElementById(feedbackGuess).innerHTML="<i class = \"icon-remove\"></i> " + guessMsg;
        document.getElementById(feedbackGuess).className="error-guess";
        
    }
}