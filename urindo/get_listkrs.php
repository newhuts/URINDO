<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
$stat = $_GET['status'];
// get all products from products table
$result = mysql_query("SELECT *, sum(matkul.sks) FROM krs, mahasiswa, matkul where matkul.kode_mk = krs.kodemk and krs.npm = mahasiswa.npm and status = '$stat' GROUP BY mahasiswa.npm ORDER BY krs.id ASC") or die(mysql_error());
// SELECT *, sum(matkul.sks) FROM krs, mahasiswa, matkul where matkul.kode_mk = krs.kodemk and krs.npm = mahasiswa.npm and status = 0 GROUP BY mahasiswa.npm ORDER BY krs.id ASC
// SELECT * FROM krs, mahasiswa where krs.npm = mahasiswa.npm and status = 0 GROUP BY mahasiswa.npm ORDER BY id ASC

if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["krs"] = array();
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $terima = array();
            $terima["id"] = $row["id"];
            $terima["npm"] = $row["npm"];
            $terima["nama"] = $row["nama"];
            $terima["smt"] = $row["smt"];
            $terima["sum(matkul.sks)"] = $row["sum(matkul.sks)"];

        // push single product into final response array
        array_push($response["krs"], $terima);
    }
    // success
    $response["success"] = 1;
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "tidak ada permintaan";
    // echo no users JSON
    echo json_encode($response);
}
?>
