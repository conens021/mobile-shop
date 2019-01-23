<%@include file="begin.jsp" %>
<div class="productForm">
    <div class="nestedProductForm">
        <form method="post" enctype="multipart/form-data" action="products">
            <label for="pName" > * Product name</label><input type="text" name="pName" />
            <label>* Product manufacturer
                <select name="manufact" style="margin-top: 1em;">
                    <option value="0">Choose product manufacturer</option>
                    <c:forEach items = "${manuf}" var = "m">
                        <option value="${m.getManufId()}">${m.getManufName()}</option>
                    </c:forEach>	
                </select>
            </label>
            <label for="price" >* Price ($)</label><input type="number" name="price"  step="0.01"/>
            <label for="sDiscount">Super discount($)</label> <input type="number" name="sDiscount"  step="0.01"/>
            <label for="desc"> Description </label> 
            <textarea placeholder="Describe product here..." name="desc" rows="10" cols="40"></textarea>
            <label for="prodPict">* Upload product picture</label>
            <input name= "prodPict" type="file" style="display: block;margin: 1em 0;"/>
            <input id = "buyButton"type="submit" value="Post product"/>
        </form>
    </div>

</div>
<!--ENd products -->
<%@include  file="end.jsp" %>