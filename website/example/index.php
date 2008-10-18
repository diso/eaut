<?php
	$page_title = 'EAUT Example';
	include '../header.php';
?>
<div id="content">
		<h1>EAUT Example</h1>

<?php
require_once 'Auth/Yadis/Email.php';

$email = $_GET['email'];

if (!empty($email)) {
	if (!isValidEmail($email)) {
		echo '<p>The email address <em>' . $email . '</em> does not appear to be valid.</p>';
	} else {
		list($user, $domain) = split('@', $email, 2);
		$fetcher = Auth_Yadis_Yadis::getHTTPFetcher();

		echo '<ul>';

		echo '<li>Attempting discovery on <em>http://' . $domain . '</em> ... ';
		$services = Auth_Yadis_Email_getServices('http://' . $domain, $fetcher);
		echo (empty($services) ? 'failed' : 'success') . '</li>';

		if (empty($services)) {
			echo '<li>Attempting discovery on <em>http://www.' . $domain . '</em> ... ';
			$services = Auth_Yadis_Email_getServices('http://www.' . $domain, $fetcher);
			echo (empty($services) ? 'failed' : 'success') . '</li>';

			if (empty($services)) {
				echo '<li>Attempting discovery on <em>' . Auth_Yadis_Default_Email_Mapper . '</em> ... ';
				$services = Auth_Yadis_Email_getServices(Auth_Yadis_Default_Email_Mapper, $fetcher);
				echo (empty($services) ? 'failed' : 'success') . '</li>';
			}
		}

		if (empty($services)) {
			echo '</ul><p>Unable to discover a valid EAUT service for email address <em>' . $email . '</em></p>';
		} else {
			foreach ($services as $s) {
				$types = $s->getTypes();
				$uris = $s->getURIs();

				if (empty($types) || empty($uris)) {
					continue;
				}

				foreach ($types as $t) {
					switch ($t) {
						case Auth_Yadis_EAUT_Template_Type:
							echo '<li>Found EAUT Template: <em>' . urldecode($uris[0]) . '</em></li>';
							$id =  str_replace(Auth_Yadis_EAUT_Wildcard_Username, $user, urldecode($uris[0]));
							echo '<li>Using EAUT Template URL: <em>' . $id . '</em></li>';
							break 3;

						case Auth_Yadis_EAUT_Mapper_Type:
							echo '<li>Found EAUT Mapping Service: <em>' . $uris[0] . '</em></li>';
							$url_parts = parse_url($uris[0]);

							if (empty($url_parts['query'])) {
								$id = $uris[0] . '?email=' . $email;
							} else {
								$id =  $uris[0] . '&email=' . $email;
							}   
								
							if ($site_name) {
								$id .= "&site_name=$site_name";
							}   

							echo '<li>Using Mapping Service URL: <em>' . $id . '</em></li>';
							break 3;
					}

				}
			}

			if (empty($id)) {
				echo '</ul><p>Unable to translate the email address <em>' . $email . '</em></p>';
			} else {
				$redirects = getRedirectPath($id);
				if (sizeof($redirects) > 1) {
					for($i=1; $i<sizeof($redirects); $i++) {
						echo '<li>URL <em>' . $redirects[$i-1] . '</em> redirects to the URL <em>' . $redirects[$i] . '</em></li>';
					}
				}
			
				// setup curl connection
				$url = $redirects[sizeof($redirects)-1];
				$ch = curl_init($url);
				curl_setopt($ch, CURLOPT_HEADER, true);
				curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

				$result = curl_exec($ch);
				$response_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);

				if ($response_code == '400') {
					echo '<li>URL <em>' . $url . '</em> resulted in an HTTP 400 error.</li>';
				} elseif ($response_code == '500') {
					echo '<li>URL <em>' . $url . '</em> resulted in an HTTP 500 error.</li>';
				}
				echo '</ul>';

				if ($response_code == '500' || $response_code =='400') {
					echo '<p>The email address <em>' . $email . '</em> was unable to be translated into a URL</p>';
				} else {
					echo '<p>The email address <em>' . $email . '</em> translates to the URL <em>' . $redirects[sizeof($redirects)-1] . '</em></p>';
				}
			}
		}
	}
}
?>

		<form>
			<label for="email">Enter an Email Address:</label>
			<input type="text" id="email" name="email" /><br />
			<input type="submit" />
		</form>

		<p>For example, try <a href="?email=will@norris.name">will@norris.name</a> or <a href="?email=david%40sappenin.com">david@sappenin.com</a>.</p>
		
</div> <!-- // #content -->

<?php
	include '../footer.php';

function isValidEmail($email) {
	return eregi("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$", $email);
}

function getRedirectPath($url) {
    $redirects = array();

    while ($url !== false) {
		$redirects[] = $url;

        // setup curl connection
        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_HEADER, true);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        //curl_setopt($ch, CURLOPT_FOLLOWLOCATION, false);

        $result = curl_exec($ch);
        if ($result !== false && curl_getinfo($ch, CURLINFO_HTTP_CODE) == '302') {
			preg_match('/^location: (.+)$/im', $result, $matches);
			$url = $matches[1];
		} else {
			break;
		}

        // cleanup
        curl_close($ch);
    }

	return $redirects;
}

?>
