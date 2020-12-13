// time
function addNow() {
  nowDate = moment().tz("Europe/London").format('YYYY-MM-DD');
  nowTime = moment().tz("Europe/London").format('HH:mm:ss');
  document.getElementById('fromDate').value = nowDate;
  document.getElementById('fromTime').value = nowTime;
  set = setTimeout(function () { addNow(); }, 1000);
}

function stopNow() {
  clearTimeout(set);
}

function addNow1() {
	  nowDate = moment().tz("Europe/London").format('YYYY-MM-DD');
	  nowTime = moment().tz("Europe/London").format('HH:mm:ss');
	  document.getElementById('toDate').value = nowDate;
	  document.getElementById('toTime').value = nowTime;
	  set = setTimeout(function () { addNow1(); }, 1000);
	}

	function stopNow1() {
	  clearTimeout(set);
	}