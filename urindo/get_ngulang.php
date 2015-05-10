<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
if (isset($_GET["npm"])) {
    $npm = $_GET['npm'];
    $oddeven = $_GET['oddeven'];
$result = mysql_query("SELECT * FROM khs, matkul where khs.kodemk = matkul.kode_mk and khs.npm = '$npm' and matkul.smt%2= '$oddeven' and khs.nilai < 2 ") or die(mysql_error());
//$result = mysql_query("SELECT *, sum(matkul.sks) FROM matkul WHERE smt ='$smt' ORDER BY idm ASC ") or die(mysql_error());
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["transkip"] = array();
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $permintaan = array();
            $permintaan["idm"] = $row["idm"];
            $permintaan["kodemk"] = $row["kodemk"];
            $permintaan["mk"] = $row["mk"];
            $permintaan["sks"] = $row["sks"];
            $permintaan["smt"] = $row["smt"];
            $permintaan["nilai"] = $row["nilai"];
            
        // push single product into final response array
        array_push($response["transkip"], $permintaan);
    }
    // success
    $response["success"] = 1;
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "tidak ada matkul";
    // echo no users JSON
    echo json_encode($response);
}
}
?>
