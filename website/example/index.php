<html>
	<head>
		<title>EAUT Example</title>
	</head>
	<body>
	
		<h1>EAUT Example</h1>

<?php
require_once 'Auth/Yadis/Email.php';

$email = $_GET['email'];

if (!empty($email)) {
	if (isValidEmail($email)) {
		$url = Auth_Yadis_Email_getID($email);
		if (empty($url)) {
			echo '<p>Unable to translate the email address <em>' . $email . '</em></p>';
		} else {
			echo '<p>The email address <em>' . $email . '</em> translates to the URL <em>' . $url . '</em></p>';
		}
	} else {
		echo '<p>The email address <em>' . $email . '</em> does not appear to be valid.</p>';
	}
}
?>

		<form>
			<label for="email">Enter an Email Address:</label>
			<input type="text" id="email" name="email" /><br />
			<input type="submit" />
		</form>

	</body>
</html>
<?php
function isValidEmail($email){
	return eregi("^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$", $email);
}
?>
