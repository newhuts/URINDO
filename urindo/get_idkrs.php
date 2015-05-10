<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
if (isset($_GET["npm"])) {
    $npm = $_GET['npm'];
    $stat = $_GET['status'];
$result = mysql_query("SELECT * FROM krs, matkul WHERE krs.kodemk = matkul.kode_mk and krs.npm ='$npm' and krs.status = '$stat' ORDER BY krs.id ASC ") or die(mysql_error());
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["matkul"] = array();
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $permintaan = array();
            $permintaan["id"] = $row["id"];
            $permintaan["idm"] = $row["idm"];
            $permintaan["npm"] = $row["npm"];
            $permintaan["kodemk"] = $row["kodemk"];
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
