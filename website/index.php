<?php
	$page_title = 'Email Address to URL Translation';
	include './header.php';
?>

<div id="content">
		<h1>Email Address to URL Translation (EAUT)</h1>

		<p>An open protocol to allow standard email addresses to be transformed
		into URLs for services like OpenID.</p>

		<h2>Why?</h2>

		<p>One of the greatest advantages of using URLs as identifiers for
		individuals is the ability to "hang" additional services off of that
		URL.  A user can specify which authentication provider they use, which
		contact service, etc.  Perhaps the greatest disadvantage of URLs, as
		we've learned in the early years of OpenID, is that users are simply
		not accustomed to using them as identifiers for themselves.  Email
		addresses on the other hand, have been used as user identifiers for
		some time.  The Email Address to URL Translation specification defines
		a method to convert users' email addresses to URLs for use in services
		such as OpenID authentication.</p>

		<h2>How does it work?</h2>
		
		<p>EAUT is designed to work in a distributed fashion, so that no one
		authority controls it.  Every email provider can control how email
		addresses at their domain are resolved into URLs.</p>

		<h2>What happens when translation fails?</h2>

		<p>While EAUT is designed to be completely decentralized, it will take
		some time for email providers to add support.  In these cases, a
		fallback service can be used which can transalte ANY email address to
		an URL.  Relying parties can use any fallback service they wish, but <a
			href="http://emailtoid.net/" target="_blank">Email to ID</a> is
		recommended.  The need for any fallback service will diminish as email
		providers begin to support EAUT natively.</p>

</div> <!-- // #content -->

<?php
	include './footer.php';
?>
