<?php header('Content-type: text/html; charset=us-ascii'); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <!-- browsers should not cache this document -->
  <meta http-equiv="Pragma" content="no-cache"/>
  <!-- date for search engines to revisit, or for caching to refresh -->
  <meta http-equiv="Expires" content="-1"/>
  <title>4DBB - 4D Building Blocks</title>
  <meta name="description"
  content="   4DBB is an interactive game to explore 4 (spatial) dimensional space.   You can train your intuition to visualize and manipulate four dimensional   shapes built of 4d cubes (tesseracts).   See the hyperspace with the tetronians eye!"/>
  <meta name="keywords"
  content="4dbb, 4d, visualization, game, 4d space, 4th dimension, 4 dimensional, flatland, fourth dimension, four dimensional, hypercube, hyperspace, tesseract"/>
  <meta name="distribution" content="global"/>
  <link href="4dbb.css" rel="stylesheet" type="text/css"/>
  <link href="gb.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<div style="text-align: right;">
<a href="http://www.nosoftwarepatents.com/"><img alt="NO SOFTWARE PATENTS"
src="nswpat80x15.gif" style="border-width: 0;" />
</a></div>

<div style="text-align: center;">
<h1>4D Building Blocks</h1>
<img alt="4d cube" src="4dcube.jpg" />


<div class="main">
4D Building Blocks is a game to interactively explore 4 dimensional space
 like a child with building blocks.
The aim is to reassemble given pieces to the goal shape.</p>
<p>
Have a look at some <a href="screenshots.html">screenshots</a>,
see the <a href="documentation.html">documentation</a>, <a href="about.html">about page</a> 
or the <a href="CHANGELOG.txt">change log</a> for the actual version @release_version@.</p>
<h4>3 choices to start the game</h4>
<a href="4dbb.jnlp">With Java web start</a> (automatic download).<br/>
<a href="applet.html">As Java applet</a> (embedded in the browser).<br/>
<a href="download.php">After download</a> (manual download).<br/>
<p>
<h3>Prerequisites</h3>
<ul style="text-align: left">
<li><b>Software</b>: At least @jre_html@.</li>
<li><b>Viewing</b>: <u>Red-cyan anaglyph glasses</u> are highly recommended. They are of negligible expense and you can use them to see
the wealth of 3d anaglyph pictures out there in the web. Just google for 
<a href="http://www.google.de/search?q=red%2Fcyan+anaglyph+stereo+3d+glasses">red/cyan anaglyph stereo 3D glasses</a> to find a seller in your area.
<br/>
Alternatively there is an <u>autostereographic</u> mode. To use this mode you have to be able to see those 
<a href="http://en.wikipedia.org/wiki/Autostereogram">autostereogram</a>s/magic eye pictures and need no additional equipment.</li>
<li><b>Knowledge</b>: This <a href="">4D Visualization</a> introduction is sufficient to start with the game. 
There is also a great educational movie <a href="http://www.dimensions-math.org/">Dimensions</a> on 4-dimensionality,
and of course <a href="http://en.wikipedia.org/wiki/4th_dimension">Wikipedia</a> is always your friend.</li>
</ul>

<p>
Discuss 4D Building Blocks <a href="@feedback_url@">here</a> (at the Tetraspace forum). </p>
<p>
You are encouraged to support further development by making a <a
href="donation.html">donation</a>.
</div>

<?php
$PHP_SELF='/phpbook/guestbook.php';
chdir('../phpbook');
include 'gbtable.php';
?>

<hr/>
<i>Last modified: <?php echo date( "Y-m-d.", getlastmod() ); ?> </i><br/>
<i>Comments and suggestions to</i> @email@<br/>
<i>@copyright@</i><br/>
<p>
  <a href="http://validator.w3.org/check?uri=referer"><img
      src="http://www.w3.org/Icons/valid-xhtml10"
      alt="Valid XHTML 1.0 Strict" height="31" width="88" /></a>
</p>
</div>
</body>
</html>
