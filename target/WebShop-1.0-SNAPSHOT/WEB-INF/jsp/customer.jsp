    <%@include file="begin.jsp" %>
<div class="productForm">
    <div class="nestedProductForm">
        <form method="post"  action="customer">
            <label for="fName" > * First name</label><input type="text" name="fName" />
            <label for="lName" > * Last name</label><input type="text" name="lName" />
            <label for="email" > * Email</label><input type="email" name="email" />
            <label>* Gender
                <select name="gender" style="margin-top: 1em;">
                    <option value="0">Choose your gender</option>
                    <option value="male">MALE</option>	
                    <option value="female">FEMALE</option>	
                </select>
            </label>
            <label for="address" >* Address</label><input type="text" name="address"/>
            <label for="city">* City</label> <input type="text" name="city"/>
            <label for="phoneNumb">* Phone number</label> <input type="text" name="phoneNumb"/>
            <label for="note"> Add some note about order </label> 
            <textarea placeholder="Type here..." name="note" rows="10" cols="40"></textarea>
            
            <input id = "buyButton"type="submit" value="Send order"/>
        </form>
    </div>

</div>
<!--ENd products -->
<%@include  file="end.jsp" %>