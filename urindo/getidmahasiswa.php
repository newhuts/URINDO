<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
// check for post data
    $us = $_GET['username'];
    // get a product from products table
    $result = mysql_query("SELECT * FROM mahasiswa WHERE npm = '$us' " );
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
            $result = mysql_fetch_array($result);
            $mahasiswa = array();
            $mahasiswa["npm"] = $result["npm"];
            $mahasiswa["nama"] = $result["nama"];
            $mahasiswa["password"] = $result["password"];
            $mahasiswa["smt"] = $result["smt"];
            $mahasiswa["krs"] = $result["krs"];
            $mahasiswa["totalkrs"] = $result["totalkrs"];
            // success
            $response["success"] = 1;
            // user node
            $response["mahasiswa"] = array();
            array_push($response["mahasiswa"], $mahasiswa);
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "Tidak ada Mahasiswa";
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "Mahasiswa tidak ditemukan";
        // echo no users JSON
        echo json_encode($response);
    }
?>