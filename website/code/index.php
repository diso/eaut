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

		<h2>Java</h2>

		<p>This library incorporates various pre-existing open-source libraries, and provides a Java-based implementation of EAUT (note that it
		currently utilizes Yadis code for discovery, though this will change once XRDS-Simple becomes finalized)</p>

		<ul>
					<li>Maven2 Repository<BR>
						&lt;repository&gt;<BR>
						&nbsp;&nbsp;&nbsp;&lt;id&gt;sappenin-repo&lt;/id&gt;<BR>
						&nbsp;&nbsp;&nbsp;&lt;name&gt;Sappenin.com Maven2 Repository&lt;/name&gt;<BR>
						&nbsp;&nbsp;&nbsp;&lt;url&gt;http://www.sappenin.com/maven2&lt;/url&gt;<BR>
						&lt;/repository&gt;<BR>
					</li>
					<li>Artificats<BR>
						&lt;groupId&gt;com.sappenin.eaut&lt;/groupId&gt;<BR>
						&lt;artifactId&gt;eaut4java&lt;/artifactId&gt;<BR>
						&lt;version&gt;0.0.5&lt;/version&gt;<BR>
					</li>
					<li>Example:
						<div style="margin-left: 1em;">
							String email = "beth@example.com";<BR>
							String mappedUrl = new EAUTConsumerImpl().doEAUT(email);<BR>
						</div>
					</li>
			</li>
		</ul>

</div> <!-- // #content -->

<?php
	include '../footer.php';
?>