<?php
	$page_title = 'EAUT Libraries';
	include '../header.php';
?>
<div id="content">
		<h1>EAUT Libraries</h1>

		<h2>PHP</h2>

		<p>This library is built on top of JanRain's excellent <a
			href="http://openidenabled.com/php-openid/">OpenID library</a> (for
		XRDS discovery) and requires version 2.x.x to function.</p>

		<ul>
			<li><a href="http://eaut.googlecode.com/svn/code/php/trunk/">PHP EAUT Library</a></li>
			<li>Example OpenID login:
				<div style="margin-left: 1em;">
<?php $code = <<<EOT
<?php
	require_once 'Auth/Yadis/Email.php';

	\$url = Auth_Yadis_Email_getID("bob@example.com");
	\$consumer->begin(\$url); //consumer is an Auth_OpenID_Consumer object
?>
EOT;
highlight_string($code);
?>
				</div>
			</li>
		</ul>

		<h2>Python</h2>

		<p>This library is built on top of JanRain's excellent <a
			href="http://openidenabled.com/python-openid/">OpenID library</a> (for
		XRDS discovery) and requires version 2.x.x to function.</p>

		<ul>
			<li><a href="http://eaut.googlecode.com/svn/code/python/trunk/eaut.py">Python EAUT Library</a></li>
		</ul>

		<h2>Ruby on Rails</h2>

		<ul>
			<li><a href="http://eaut.googlecode.com/svn/code/rails/trunk/">Ruby on Rails EAUT Library</a></li>
		</ul>

</div> <!-- // #content -->

<?php
	include '../footer.php';
?>