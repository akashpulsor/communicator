<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en" class="no-js">
<!-- Begin Head -->
<head>

<!-- let's add tag srping:url -->
<spring:url value="/WEB-INF/resources" var="resources" />
<spring:url value="/WEB-INF/lib/webjars/jquery/2.1.1/"
	var="webjarJquery" />
<spring:url value="/WEB-INF/lib/webjars/bootstrap/3.0.0/"
	var="bootstarp" />
<spring:url value="/WEB-INF/resources/theme1/" var="theme1" />
<spring:url value="/WEB-INF/resources/theme1/vendor" var="vendor" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<!-- Finish adding tags -->

<!-- Basic -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Communicator</title>
<meta name="keywords" content="HTML5 Theme" />
<meta name="description" content="Megakit - HTML5 Theme">
<meta name="author" content="keenthemes.com">

<!-- Web Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,400i|Montserrat:400,700"
	rel="stylesheet">

<!-- Vendor Styles -->
<link href="lib/webjars/bootstrap/3.0.0/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="resources/theme1/css/animate.css" rel="stylesheet"
	type="text/css" />
<link href="resources/theme1/vendor/themify/themify.css"
	rel="stylesheet" type="text/css" />
<link href="resources/theme1/vendor/scrollbar/scrollbar.min.css"
	rel="stylesheet" type="text/css" />
<link href="resources/theme1/vendor/magnific-popup/magnific-popup.css"
	rel="stylesheet" type="text/css" />
<link href="resources/theme1/vendor/swiper/swiper.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="resources/theme1/vendor/cubeportfoliof/css/cubeportfolio.min.css"
	rel="stylesheet" type="text/css" />

<!-- Theme Styles -->
<link href="resources/theme1/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="resources/theme1/css/global/global.css" rel="stylesheet"
	type="text/css" />

<!-- Favicon -->
<link rel="shortcut icon" href="/resources/img/favicon.ico"
	type="image/x-icon">
<link rel="apple-touch-icon" href="/resources/img/apple-touch-icon.png">
</head>
<!-- End Head -->

<!-- Body -->
<body>

	<!--========== HEADER ==========-->

	<header
		class="navbar-fixed-top s-header js__header-sticky js__header-overlay">
	<!-- Navbar -->
	<div class="s-header__navbar">
		<div class="s-header__container">
			<div class="s-header__navbar-row">
				<div class="s-header__navbar-row-col">
					<!-- Logo -->

					<div class="s-header__logo">
						<a href="index.html" class="s-header__logo-link"> <img
							class="s-header__logo-img s-header__logo-img-default"
							src="resources/theme1/img/logo.png" alt="Megakit Logo"> <img
							class="s-header__logo-img s-header__logo-img-shrink"
							src="resources/theme1/img/logo-dark.png" alt="Megakit Logo">
						</a>
					</div>
					<!-- End Logo -->
				</div>
				<div class="s-header__navbar-row-col">
					<!-- Trigger -->
					<!-- Feedback Form -->
					<div class="g-bg-color--sky-light">
						<div class="container g-padding-y-80--xs g-padding-y-125--sm">

							<form:form action="login.html" method="post">
								<div class="row g-margin-b-40--xs">
									<div class="col-sm-6 g-margin-b-20--xs g-margin-b-0--md">

										<div class="g-margin-b-20--xs">
											<input type="email"
												class="form-control s-form-v2__input g-radius--50"
												placeholder="* Email/Phone"> <input type="text"
												class="form-control s-form-v2__input g-radius--50"
												placeholder="* Password">
										</div>

									</div>

								</div>
								<div class="g-text-center--xs">
									<button type="submit"
										class="text-uppercase s-btn s-btn--md s-btn--primary-bg g-radius--50 g-padding-x-80--xs">Login</button>
								</div>
							</form:form>
						</div>
					</div>

					<!-- End Trigger -->
				</div>
			</div>
		</div>
	</div>
	<!-- End Navbar --> <!-- Overlay -->
	<div class="s-header-bg-overlay js__bg-overlay">
		<!-- Nav -->
		<nav class="s-header__nav js__scrollbar">
		<div class="container-fluid">
			<!-- Menu List -->
			<ul class="list-unstyled s-header__nav-menu">
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider -is-active"
					href="index.html">Corporate</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="index_app_landing.html">App Landing</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="index_portfolio.html">Portfolio</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="index_events.html">Events</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="index_lawyer.html">Lawyer</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="index_clinic.html">Clinic</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="index_coming_soon.html">Coming Soon</a></li>
			</ul>
			<!-- End Menu List -->

			<!-- Menu List -->
			<ul class="list-unstyled s-header__nav-menu">
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="about.html">About</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="team.html">Team</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="services.html">Services</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="events.html">Events</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="faq.html">FAQ</a></li>
				<li class="s-header__nav-menu-item"><a
					class="s-header__nav-menu-link s-header__nav-menu-link-divider"
					href="contacts.html">Contacts</a></li>
			</ul>
			<!-- End Menu List -->
		</div>
		</nav>
		<!-- End Nav -->

		<!-- Action -->
		<ul class="list-inline s-header__action s-header__action--lb">
			<li class="s-header__action-item"><a
				class="s-header__action-link -is-active" href="#">En</a></li>
			<li class="s-header__action-item"><a
				class="s-header__action-link" href="#">Fr</a></li>
		</ul>
		<!-- End Action -->

		<!-- Action -->
		<ul class="list-inline s-header__action s-header__action--rb">
			<li class="s-header__action-item"><a
				class="s-header__action-link" href="#"> <i
					class="g-padding-r-5--xs ti-facebook"></i> <span
					class="g-display-none--xs g-display-inline-block--sm">Facebook</span>
			</a></li>
			<li class="s-header__action-item"><a
				class="s-header__action-link" href="#"> <i
					class="g-padding-r-5--xs ti-twitter"></i> <span
					class="g-display-none--xs g-display-inline-block--sm">Twitter</span>
			</a></li>
			<li class="s-header__action-item"><a
				class="s-header__action-link" href="#"> <i
					class="g-padding-r-5--xs ti-instagram"></i> <span
					class="g-display-none--xs g-display-inline-block--sm">Instagram</span>
			</a></li>
		</ul>
		<!-- End Action -->
	</div>
	<!-- End Overlay --> </header>
	<!--========== END HEADER ==========-->

	<!--========== SWIPER SLIDER ==========-->
	<div class="s-swiper js__swiper-one-item">
		<!-- Swiper Wrapper -->
		<div class="swiper-wrapper">
			<div class="g-fullheight--xs g-bg-position--center swiper-slide"
				style="background: url('${images}/1920x1080/02.jpg');">
				<div class="container g-text-center--xs g-ver-center--xs">
					<div class="g-margin-b-30--xs">
						<h1
							class="g-font-size-35--xs g-font-size-45--sm g-font-size-55--md g-color--white">
							A Mobile Experience<br>That Inspires Travel
						</h1>
					</div>
					<a class="js__popup__youtube"
						href="https://www.youtube.com/watch?v=lcFYdgZKZxY"
						title="Intro Video"> <i
						class="s-icon s-icon--lg s-icon--white-bg g-radius--circle ti-control-play"></i>
					</a>
				</div>
			</div>
			<div class="g-fullheight--xs g-bg-position--center swiper-slide"
				style="background: url('img/1920x1080/01.jpg');">
				<div class="container g-text-center--xs g-ver-center--xs">
					<div class="g-margin-b-30--xs">
						<div class="g-margin-b-30--xs">
							<h2
								class="g-font-size-35--xs g-font-size-45--sm g-font-size-55--md g-color--white">
								We Craft Experience<br>That Help Brands<br>Stand Out
							</h2>
						</div>
						<a class="js__popup__youtube"
							href="https://www.youtube.com/watch?v=lcFYdgZKZxY"
							title="Intro Video"> <i
							class="s-icon s-icon--lg s-icon--white-bg g-radius--circle ti-control-play"></i>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- End Swiper Wrapper -->

		<!-- Arrows -->
		<a href="javascript:void(0);"
			class="s-swiper__arrow-v1--right s-icon s-icon--md s-icon--white-brd g-radius--circle ti-angle-right js__swiper-btn--next"></a>
		<a href="javascript:void(0);"
			class="s-swiper__arrow-v1--left s-icon s-icon--md s-icon--white-brd g-radius--circle ti-angle-left js__swiper-btn--prev"></a>
		<!-- End Arrows -->

		<a href="#js__scroll-to-section"
			class="s-scroll-to-section-v1--bc g-margin-b-15--xs"> <span
			class="g-font-size-18--xs g-color--white ti-angle-double-down"></span>
			<p
				class="text-uppercase g-color--white g-letter-spacing--3 g-margin-b-0--xs">Learn
				More</p>
		</a>
	</div>
	<!--========== END SWIPER SLIDER ==========-->

	<!--========== PAGE CONTENT ==========-->
	<!-- Features -->
	<div id="js__scroll-to-section"
		class="container g-padding-y-80--xs g-padding-y-125--sm">
		<div class="g-text-center--xs g-margin-b-100--xs">
			<p
				class="text-uppercase g-font-size-14--xs g-font-weight--700 g-color--primary g-letter-spacing--2 g-margin-b-25--xs">Welcome
				to Megakit</p>
			<h2 class="g-font-size-32--xs g-font-size-36--md">
				We Create Beautiful Experiences <br> That Drive Successful
				Businesses.
			</h2>
		</div>
		<div class="row g-margin-b-60--xs g-margin-b-70--md">
			<div class="col-sm-4 g-margin-b-60--xs g-margin-b-0--md">
				<div class="clearfix">
					<div class="g-media g-width-30--xs">
						<div class="wow fadeInDown" data-wow-duration=".3"
							data-wow-delay=".1s">
							<i class="g-font-size-28--xs g-color--primary ti-desktop"></i>
						</div>
					</div>
					<div class="g-media__body g-padding-x-20--xs">

						<h3 class="g-font-size-18--xs">Responsive Layout</h3>
						<p class="g-margin-b-0--xs">This is where we sit down, grab a
							cup of coffee and dial in the details.</p>
					</div>
				</div>
			</div>
			<div class="col-sm-4 g-margin-b-60--xs g-margin-b-0--md">
				<div class="clearfix">
					<div class="g-media g-width-30--xs">
						<div class="wow fadeInDown" data-wow-duration=".3"
							data-wow-delay=".2s">
							<i class="g-font-size-28--xs g-color--primary ti-settings"></i>
						</div>
					</div>
					<div class="g-media__body g-padding-x-20--xs">
						<h3 class="g-font-size-18--xs">Fully Customizable</h3>
						<p class="g-margin-b-0--xs">This is where we sit down, grab a
							cup of coffee and dial in the details.</p>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="clearfix">
					<div class="g-media g-width-30--xs">
						<div class="wow fadeInDown" data-wow-duration=".3"
							data-wow-delay=".3s">
							<i class="g-font-size-28--xs g-color--primary ti-ruler-alt-2"></i>
						</div>
					</div>
					<div class="g-media__body g-padding-x-20--xs">
						<h3 class="g-font-size-18--xs">Pixel Perfect</h3>
						<p class="g-margin-b-0--xs">This is where we sit down, grab a
							cup of coffee and dial in the details.</p>
					</div>
				</div>
			</div>
		</div>
		<!-- // end row  -->
		<div class="row">
			<div class="col-sm-4 g-margin-b-60--xs g-margin-b-0--md">
				<div class="clearfix">
					<div class="g-media g-width-30--xs">
						<div class="wow fadeInDown" data-wow-duration=".3"
							data-wow-delay=".4s">
							<i class="g-font-size-28--xs g-color--primary ti-package"></i>
						</div>
					</div>
					<div class="g-media__body g-padding-x-20--xs">
						<h3 class="g-font-size-18--xs">Endless Possibilities</h3>
						<p class="g-margin-b-0--xs">This is where we sit down, grab a
							cup of coffee and dial in the details.</p>
					</div>
				</div>
			</div>
			<div class="col-sm-4 g-margin-b-60--xs g-margin-b-0--md">
				<div class="clearfix">
					<div class="g-media g-width-30--xs">
						<div class="wow fadeInDown" data-wow-duration=".3"
							data-wow-delay=".5s">
							<i class="g-font-size-28--xs g-color--primary ti-star"></i>
						</div>
					</div>
					<div class="g-media__body g-padding-x-20--xs">
						<h3 class="g-font-size-18--xs">Powerful Performance</h3>
						<p class="g-margin-b-0--xs">This is where we sit down, grab a
							cup of coffee and dial in the details.</p>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="clearfix">
					<div class="g-media g-width-30--xs">
						<div class="wow fadeInDown" data-wow-duration=".3"
							data-wow-delay=".6s">
							<i class="g-font-size-28--xs g-color--primary ti-panel"></i>
						</div>
					</div>
					<div class="g-media__body g-padding-x-20--xs">
						<h3 class="g-font-size-18--xs">Parallax Support</h3>
						<p class="g-margin-b-0--xs">This is where we sit down, grab a
							cup of coffee and dial in the details.</p>
					</div>
				</div>
			</div>
		</div>
		<!-- // end row  -->
	</div>
	<!-- End Features -->





	<!-- End Subscribe -->




	<!-- Testimonials -->
	<div class="js__parallax-window"
		style="background: url(/resources/img/1920x1080/04.jpg) 50% 0 no-repeat fixed;">
		<div
			class="container g-text-center--xs g-padding-y-80--xs g-padding-y-125--sm">
			<p
				class="text-uppercase g-font-size-14--xs g-font-weight--700 g-color--white-opacity g-letter-spacing--2 g-margin-b-50--xs">Testimonials</p>
			<div class="s-swiper js__swiper-testimonials">
				<!-- Swiper Wrapper -->
				<div class="swiper-wrapper g-margin-b-50--xs">
					<div class="swiper-slide g-padding-x-130--sm g-padding-x-150--lg">
						<div class="g-padding-x-20--xs g-padding-x-50--lg">
							<div class="g-margin-b-40--xs">
								<p class="g-font-size-22--xs g-font-size-28--sm g-color--white">
									<i>" I have purchased many great templates over the years
										but this product and this company have taken it to the next
										level. Exceptional customizability. "</i>
								</p>
							</div>
							<div
								class="center-block g-hor-divider__solid--white-opacity-lightest g-width-100--xs g-margin-b-30--xs"></div>
							<h4
								class="g-font-size-15--xs g-font-size-18--sm g-color--white-opacity-light g-margin-b-5--xs">Jake
								Richardson / Google</h4>
						</div>
					</div>
					<div class="swiper-slide g-padding-x-130--sm g-padding-x-150--lg">
						<div class="g-padding-x-20--xs g-padding-x-50--lg">
							<div class="g-margin-b-40--xs">
								<p class="g-font-size-22--xs g-font-size-28--sm g-color--white">
									<i>" I have purchased many great templates over the years
										but this product and this company have taken it to the next
										level. Exceptional customizability. "</i>
								</p>
							</div>
							<div
								class="center-block g-hor-divider__solid--white-opacity-lightest g-width-100--xs g-margin-b-30--xs"></div>
							<h4
								class="g-font-size-15--xs g-font-size-18--sm g-color--white-opacity-light g-margin-b-5--xs">Jake
								Richardson / Google</h4>
						</div>
					</div>
					<div class="swiper-slide g-padding-x-130--sm g-padding-x-150--lg">
						<div class="g-padding-x-20--xs g-padding-x-50--lg">
							<div class="g-margin-b-40--xs">
								<p class="g-font-size-22--xs g-font-size-28--sm g-color--white">
									<i>" I have purchased many great templates over the years
										but this product and this company have taken it to the next
										level. Exceptional customizability. "</i>
								</p>
							</div>
							<div
								class="center-block g-hor-divider__solid--white-opacity-lightest g-width-100--xs g-margin-b-30--xs"></div>
							<h4
								class="g-font-size-15--xs g-font-size-18--sm g-color--white-opacity-light g-margin-b-5--xs">Jake
								Richardson / Google</h4>
						</div>
					</div>
				</div>
				<!-- End Swipper Wrapper -->

				<!-- Arrows -->
				<div
					class="g-font-size-22--xs g-color--white-opacity js__swiper-fraction"></div>
				<a href="javascript:void(0);"
					class="g-display-none--xs g-display-inline-block--sm s-swiper__arrow-v1--right s-icon s-icon--md s-icon--white-brd g-radius--circle ti-angle-right js__swiper-btn--next"></a>
				<a href="javascript:void(0);"
					class="g-display-none--xs g-display-inline-block--sm s-swiper__arrow-v1--left s-icon s-icon--md s-icon--white-brd g-radius--circle ti-angle-left js__swiper-btn--prev"></a>
				<!-- End Arrows -->
			</div>
		</div>
	</div>
	<!-- End Testimonials -->

	<!-- Clients -->
	<div class="g-bg-color--sky-light">
		<div class="g-container--md g-padding-y-80--xs g-padding-y-125--sm">
			<!-- Swiper Clients -->
			<div class="s-swiper js__swiper-clients">
				<div class="swiper-wrapper"></div>
			</div>
			<!-- End Swiper Clients -->
		</div>
	</div>
	<!-- End Clients -->

	<!-- News -->
	<div class="container g-padding-y-80--xs g-padding-y-125--sm">
		<div class="g-text-center--xs g-margin-b-80--xs">
			<p
				class="text-uppercase g-font-size-14--xs g-font-weight--700 g-color--primary g-letter-spacing--2 g-margin-b-25--xs">Blog</p>

		</div>
		<div class="row">
			<div class="col-sm-4 g-margin-b-30--xs g-margin-b-0--md">
				<!-- News -->
				<article> </article>
				<!-- End News -->
			</div>
			<div class="col-sm-4 g-margin-b-30--xs g-margin-b-0--md">
				<!-- News -->
				<article> </article>
				<!-- End News -->
			</div>
			<div class="col-sm-4">
				<!-- News -->
				<article> </article>
				<!-- End News -->
			</div>
		</div>
	</div>
	<!-- End News -->


	<!-- End Counter -->

	<!-- Feedback Form -->
	<div class="g-bg-color--sky-light">
		<div class="container g-padding-y-80--xs g-padding-y-125--sm">
			<div class="g-text-center--xs g-margin-b-80--xs">
				<p
					class="text-uppercase g-font-size-14--xs g-font-weight--700 g-color--primary g-letter-spacing--2 g-margin-b-25--xs">Feedback</p>
				<h2 class="g-font-size-32--xs g-font-size-36--md">Send us a
					note</h2>
			</div>
			<form>
				<div class="row g-margin-b-40--xs">
					<div class="col-sm-6 g-margin-b-20--xs g-margin-b-0--md">
						<div class="g-margin-b-20--xs">
							<input type="text"
								class="form-control s-form-v2__input g-radius--50"
								placeholder="* Name">
						</div>
						<div class="g-margin-b-20--xs">
							<input type="email"
								class="form-control s-form-v2__input g-radius--50"
								placeholder="* Email">
						</div>
						<input type="text"
							class="form-control s-form-v2__input g-radius--50"
							placeholder="* Phone">
					</div>
					<div class="col-sm-6">
						<textarea
							class="form-control s-form-v2__input g-radius--10 g-padding-y-20--xs"
							rows="8" placeholder="* Your message"></textarea>
					</div>
				</div>
				<div class="g-text-center--xs">
					<button type="submit"
						class="text-uppercase s-btn s-btn--md s-btn--primary-bg g-radius--50 g-padding-x-80--xs">Submit</button>
				</div>
			</form>
		</div>
	</div>
	<!-- End Feedback Form -->

	<!-- Google Map -->
	<section class="s-google-map">
	<div id="js__google-container"
		class="s-google-container g-height-400--xs"></div>
	</section>
	<!-- End Google Map -->
	<!--========== END PAGE CONTENT ==========-->

	<!--========== FOOTER ==========-->
	<footer class="g-bg-color--dark"> <!-- Links -->
	<div class="g-hor-divider__dashed--white-opacity-lightest">
		<div class="container g-padding-y-80--xs">
			<div class="row">
				<div class="col-sm-2 g-margin-b-20--xs g-margin-b-0--md">
					<ul class="list-unstyled g-ul-li-tb-5--xs g-margin-b-0--xs">
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">Home</a></li>
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">About</a></li>
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">Work</a></li>
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">Contact</a></li>
					</ul>
				</div>
				<div class="col-sm-2 g-margin-b-20--xs g-margin-b-0--md">
					<ul class="list-unstyled g-ul-li-tb-5--xs g-margin-b-0--xs">
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">Twitter</a></li>
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">Facebook</a></li>
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">Instagram</a></li>
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">YouTube</a></li>
					</ul>
				</div>
				<div class="col-sm-2 g-margin-b-40--xs g-margin-b-0--md">
					<ul class="list-unstyled g-ul-li-tb-5--xs g-margin-b-0--xs">
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">Subscribe
								to Our Newsletter</a></li>
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">Privacy
								Policy</a></li>
						<li><a class="g-font-size-15--xs g-color--white-opacity"
							href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes">Terms
								&amp; Conditions</a></li>
					</ul>
				</div>
				<div
					class="col-md-4 col-md-offset-2 col-sm-5 col-sm-offset-1 s-footer__logo g-padding-y-50--xs g-padding-y-0--md">
					<h3 class="g-font-size-18--xs g-color--white">Megakit</h3>
					<p class="g-color--white-opacity">We are a creative studio
						focusing on culture, luxury, editorial &amp; art. Somewhere
						between sophistication and simplicity.</p>
				</div>
			</div>
		</div>
	</div>
	<!-- End Links --> <!-- Copyright -->
	<div class="container g-padding-y-50--xs">
		<div class="row">
			<div class="col-xs-6">
				<a href="index.html"> <img
					class="g-width-100--xs g-height-auto--xs"
					src="/resources/img/logo.png" alt="Megakit Logo">
				</a>
			</div>
			<div class="col-xs-6 g-text-right--xs">
				<p
					class="g-font-size-14--xs g-margin-b-0--xs g-color--white-opacity-light">
					<a href="http://keenthemes.com/preview/Megakit/">Megakit</a> Theme
					Powered by: <a href="http://www.keenthemes.com/">KeenThemes.com</a>
				</p>
			</div>
		</div>
	</div>
	<!-- End Copyright --> </footer>
	<!--========== END FOOTER ==========-->

	<!-- Back To Top -->
	<a href="javascript:void(0);" class="s-back-to-top js__back-to-top"></a>

	<!--========== JAVASCRIPTS (Load javascripts at bottom, this will reduce page load time) ==========-->
	<!-- Vendor -->
	<script type="text/javascript"
		src="/resources/theme1/vendor/jquery.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/jquery.migrate.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/jquery.smooth-scroll.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/jquery.back-to-top.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/scrollbar/jquery.scrollbar.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/swiper/swiper.jquery.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/waypoint.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/counterup.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/cubeportfolio/js/jquery.cubeportfolio.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/jquery.parallax.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBsXUGTFS09pLVdsYEE9YrO2y4IAncAO2U"></script>
	<script type="text/javascript"
		src="/resources/theme1/vendor/jquery.wow.min.js"></script>

	<!-- General Components and Settings -->
	<script type="text/javascript" src="/resources/theme1/js/global.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/js/components/header-sticky.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/js/components/scrollbar.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/js/components/magnific-popup.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/js/components/swiper.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/js/components/counter.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/js/components/portfolio-3-col.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/js/components/parallax.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/js/components/google-map.min.js"></script>
	<script type="text/javascript"
		src="/resources/theme1/js/components/wow.min.js"></script>
	<!--========== END JAVASCRIPTS ==========-->

</body>
<!-- End Body -->
</html>
