<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Homepage | Web Shop</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <base href="/WebShop/">
        <link rel="stylesheet" type="text/css" href="resources/style/index.css">
    </head>
    <body>
        <!--Start wrapper -->
        <div class="wrapper">
            <div class="header"><ul class="headerUL"><li class="headerLI"><a href="homepage">Home</a></li><li class="headerLI"><a href="products">Upload product</a></li><li class="headerLI"><a href="admin">Admin</a></li><li class="headerLI"><a href="cart">Cart</a></li></ul></div>
            <!--Start content -->
            <div class="content">
                <!--Start nav-bar -->
                <div class="nav-bar">
                    <dl id="browse">
                        <dt>Manufacturers</dt>
                        <c:forEach items = "${manuf}" var = "m">
                            <dd><dd><a href="${m.getManufId()}">${m.getManufName()}</a></dd></dd>
                            </c:forEach>	      

                        <dt>Search Your Model</dt>
                        <dd class="searchform">
                            <form action="begin" method="get">

                                <div>
                                    <input id="serchQ" name="serchQuery" type="text" placeholder="search your model" class="text"  />
                                </div>
                                <div class="softright">
                                    <input type="submit" value="Search" />
                                </div>
                            </form>
                        </dd>
                    </dl>
                </div>
                <!--End nav-bar -->

                