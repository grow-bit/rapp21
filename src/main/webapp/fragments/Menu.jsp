<li><a href="/stats">Statistiche</a></li>
<c:if test = "${not empty logoutUrl}">
    <!-- <li><a href="/profile">Il tuo profilo</a></li> -->
    <li><a href="${logoutUrl}">Logout</a></li>
</c:if>