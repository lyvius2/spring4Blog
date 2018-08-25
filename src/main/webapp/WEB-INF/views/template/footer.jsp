<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><footer class="footer">
		<c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/'}">
		<div class="footer__block">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-sm-12">
						<h4 class="heading">About Walter's Home</h4>
						<p>일본 여행과 북유럽의 대자연을 좋아하는 평범한 개발자의 기술, 여행 이야기입니다.</p>
						<p>日本旅行と北欧の大自然が好きな普通の開発者のブログです。</p>
					</div>
					<div class="col-md-4 col-sm-12">
						<h4 class="heading">My travels</h4>
						<p>
							<i class="jp flag" title="All 47 prefectures"></i>
							<i class="tw flag" title="Taipei"></i>
							<i class="is flag" title="Reykjavik, Jokulsarlon, Golden Circle, Jökulsárgljúfur, Langjökull"></i>
							<i class="fi flag" title="Helsinki"></i>
							<i class="ee flag" title="Tallinn"></i>
							<i class="hk flag" title="Hongkong"></i><br/>
							<i class="kr flag" title="Seoul, Jeju"></i>
							<i class="se flag" title="Stockholm"></i>
							<i class="no flag" title="Bergen, Flåm, Sognefjorden, Stavanger, Pulpit Rock"></i>
							<i class="fr flag" title="Paris"></i>
							<i class="nl flag" title="Amsterdam"></i>
							<i class="gb flag" title="London, Brighton"></i><br/>
							<i class="gb sct flag" title="Edinburgh, Highland"></i>
							<i class="dk flag" title="København, Helsingør"></i>
							<i class="fo flag" title="Mykines, Sørvágur, Tórshavn, Klaksvík, Kalsoy, Gjogv"></i>
							<i class="de flag" title="Rüdesheim am Rhein"></i>
							<i class="sg flag" title="Singapore"></i>
							<i class="cn flag" title="Beijing"></i><br/>
							<i class="nz flag" title="Auckland, Queenstown, Milford Sound, Mt.Cook, Christchurch"></i>
							<i class="ca flag" title="Vancouver, Banff, Niagara Falls"></i>
						</p>
					</div>
				</div>
				<%--
				<div class="row">
					<div class="col-md-4 col-sm-6">
						<h4 class="heading">Popular Categories</h4>
						<ul>
							<li><a href="category.html">Graphic design</a></li>
							<li><a href="category.html">Web design</a></li>
							<li><a href="category.html">Support</a></li>
							<li><a href="category.html">Accounting</a></li>
						</ul>
					</div>
					<div class="col-md-4 col-sm-6">
						<h4 class="heading">Let's be Friends</h4>
						<p class="social social--big"><a href="#" data-animate-hover="pulse" class="external facebook"><i class="fa fa-facebook"></i></a><a href="#" data-animate-hover="pulse" class="external gplus"><i class="fa fa-google-plus"></i></a><a href="#" data-animate-hover="pulse" class="external twitter"><i class="fa fa-twitter"></i></a><a href="#" data-animate-hover="pulse" class="email"><i class="fa fa-envelope"></i></a></p>
					</div>
					<div class="col-md-4 col-sm-12">
						<h4 class="heading">News and Updates</h4>
						<p>Sign up to get weekly portion of fresh jobs and news from us.</p>
						<form class="footer__newsletter">
							<div class="input-group">
								<input type="text" placeholder="Enter your email address" class="form-control"><span class="input-group-btn">
	                    <button type="button" class="btn btn-default"><i class="fa fa-send"></i></button></span>
							</div>
						</form>
					</div>
				</div>
				--%>
			</div>
		</div>
		</c:if>
		<div class="footer__copyright">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<p>&copy;2016 ~ 2018 walter.hwang</p>
					</div>
					<div class="col-md-6">
						<p class="credit">Code <a href="https://bootstrapious.com/" class="external">Bootstrapious</a></p>
						<!-- Please do not remove the backlink to us unless you support us at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)-->
					</div>
				</div>
			</div>
		</div>
	</footer><c:if test="${requestSignIn == true}">
	<script>
		alert('로그인이 필요합니다.')
		$('#sign-in-headerBtn').trigger('click')
	</script></c:if>