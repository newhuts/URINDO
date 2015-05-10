<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
if (isset($_GET["smt"])) {
    $smt = $_GET['smt'];
$result = mysql_query("SELECT * FROM matkul WHERE smt ='$smt' ORDER BY idm ASC ") or die(mysql_error());
//$result = mysql_query("SELECT *, sum(matkul.sks) FROM matkul WHERE smt ='$smt' ORDER BY idm ASC ") or die(mysql_error());
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["matkul"] = array();
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $permintaan = array();
            $permintaan["idm"] = $row["idm"];
            $permintaan["kode_mk"] = $row["kode_mk"];
            $permintaan["mk"] = $row["mk"];
            $permintaan["sks"] = $row["sks"];
            $permintaan["smt"] = $row["smt"];
            
        // push single product into final response array
        array_push($response["matkul"], $permintaan);
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
