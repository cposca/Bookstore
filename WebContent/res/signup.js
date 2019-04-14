var provinces = {};
	provinces['CA'] = ["AB","BC","MB","NB","NL","NS","ON","PE","NT","QC","YT","NU"];
	provinces['USA'] = ["AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MP", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UM", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"];
	
function updateProvinces() {
	var countryList = document.getElementById("countryList");
	var provinceList = document.getElementById("provinceList");
	var selCountry = countryList.options[countryList.selectedIndex].value;
	
	while(provinceList.options.length){
		provinceList.remove(0);
	}
	
	var ps = provinces[selCountry];
	if(ps) {
		var i;
		for(i = 0; i < ps.length; i++){
			var p = new Option(ps[i],i);
			provinceList.options.add(p);
		}
	}
}