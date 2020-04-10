/// results : [{"address_components":[{"long_name":"外埔區","short_name":"外埔區","types":["administrative_area_level_3","political"]},{"long_name":"台中市","short_name":"台中市","types":["administrative_area_level_1","political"]},{"long_name":"台灣","short_name":"TW","types":["country","political"]},{"long_name":"438","short_name":"438","types":["postal_code"]}],"formatted_address":"438台灣台中市外埔區","geometry":{"bounds":{"northeast":{"lat":24.3625505,"lng":120.7194453},"southwest":{"lat":24.2991445,"lng":120.6090734}},"location":{"lat":24.3317733,"lng":120.6533224},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":24.3625505,"lng":120.7194453},"southwest":{"lat":24.2991445,"lng":120.6090734}}},"place_id":"ChIJjZHHFtMRaTQR7UY9m9IvwOA","types":["administrative_area_level_3","political"]}]
/// status : "OK"

class Json {
  List<ResultsBean> _results;
  String _status;

  List<ResultsBean> get results => _results;
  String get status => _status;

  Json(this._results, this._status);

  Json.map(dynamic obj) {
    this._results = obj["results"];
    this._status = obj["status"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["results"] = _results;
    map["status"] = _status;
    return map;
  }

}

/// address_components : [{"long_name":"外埔區","short_name":"外埔區","types":["administrative_area_level_3","political"]},{"long_name":"台中市","short_name":"台中市","types":["administrative_area_level_1","political"]},{"long_name":"台灣","short_name":"TW","types":["country","political"]},{"long_name":"438","short_name":"438","types":["postal_code"]}]
/// formatted_address : "438台灣台中市外埔區"
/// geometry : {"bounds":{"northeast":{"lat":24.3625505,"lng":120.7194453},"southwest":{"lat":24.2991445,"lng":120.6090734}},"location":{"lat":24.3317733,"lng":120.6533224},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":24.3625505,"lng":120.7194453},"southwest":{"lat":24.2991445,"lng":120.6090734}}}
/// place_id : "ChIJjZHHFtMRaTQR7UY9m9IvwOA"
/// types : ["administrative_area_level_3","political"]

class ResultsBean {
  List<Address_componentsBean> _addressComponents;
  String _formattedAddress;
  GeometryBean _geometry;
  String _placeId;
  List<String> _types;

  List<Address_componentsBean> get addressComponents => _addressComponents;
  String get formattedAddress => _formattedAddress;
  GeometryBean get geometry => _geometry;
  String get placeId => _placeId;
  List<String> get types => _types;

  ResultsBean(this._addressComponents, this._formattedAddress, this._geometry, this._placeId, this._types);

  ResultsBean.map(dynamic obj) {
    this._addressComponents = obj["addressComponents"];
    this._formattedAddress = obj["formattedAddress"];
    this._geometry = obj["geometry"];
    this._placeId = obj["placeId"];
    this._types = obj["types"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["addressComponents"] = _addressComponents;
    map["formattedAddress"] = _formattedAddress;
    map["geometry"] = _geometry;
    map["placeId"] = _placeId;
    map["types"] = _types;
    return map;
  }

}

/// bounds : {"northeast":{"lat":24.3625505,"lng":120.7194453},"southwest":{"lat":24.2991445,"lng":120.6090734}}
/// location : {"lat":24.3317733,"lng":120.6533224}
/// location_type : "APPROXIMATE"
/// viewport : {"northeast":{"lat":24.3625505,"lng":120.7194453},"southwest":{"lat":24.2991445,"lng":120.6090734}}

class GeometryBean {
  BoundsBean _bounds;
  LocationBean _location;
  String _locationType;
  ViewportBean _viewport;

  BoundsBean get bounds => _bounds;
  LocationBean get location => _location;
  String get locationType => _locationType;
  ViewportBean get viewport => _viewport;

  GeometryBean(this._bounds, this._location, this._locationType, this._viewport);

  GeometryBean.map(dynamic obj) {
    this._bounds = obj["bounds"];
    this._location = obj["location"];
    this._locationType = obj["locationType"];
    this._viewport = obj["viewport"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["bounds"] = _bounds;
    map["location"] = _location;
    map["locationType"] = _locationType;
    map["viewport"] = _viewport;
    return map;
  }

}

/// northeast : {"lat":24.3625505,"lng":120.7194453}
/// southwest : {"lat":24.2991445,"lng":120.6090734}

class ViewportBean {
  NortheastBean _northeast;
  SouthwestBean _southwest;

  NortheastBean get northeast => _northeast;
  SouthwestBean get southwest => _southwest;

  ViewportBean(this._northeast, this._southwest);

  ViewportBean.map(dynamic obj) {
    this._northeast = obj["northeast"];
    this._southwest = obj["southwest"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["northeast"] = _northeast;
    map["southwest"] = _southwest;
    return map;
  }

}

/// lat : 24.2991445
/// lng : 120.6090734

class SouthwestBean {
  double _lat;
  double _lng;

  double get lat => _lat;
  double get lng => _lng;

  SouthwestBean(this._lat, this._lng);

  SouthwestBean.map(dynamic obj) {
    this._lat = obj["lat"];
    this._lng = obj["lng"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["lat"] = _lat;
    map["lng"] = _lng;
    return map;
  }

}

/// lat : 24.3625505
/// lng : 120.7194453

class NortheastBean {
  double _lat;
  double _lng;

  double get lat => _lat;
  double get lng => _lng;

  NortheastBean(this._lat, this._lng);

  NortheastBean.map(dynamic obj) {
    this._lat = obj["lat"];
    this._lng = obj["lng"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["lat"] = _lat;
    map["lng"] = _lng;
    return map;
  }

}

/// lat : 24.3317733
/// lng : 120.6533224

class LocationBean {
  double _lat;
  double _lng;

  double get lat => _lat;
  double get lng => _lng;

  LocationBean(this._lat, this._lng);

  LocationBean.map(dynamic obj) {
    this._lat = obj["lat"];
    this._lng = obj["lng"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["lat"] = _lat;
    map["lng"] = _lng;
    return map;
  }

}

/// northeast : {"lat":24.3625505,"lng":120.7194453}
/// southwest : {"lat":24.2991445,"lng":120.6090734}

class BoundsBean {
  NortheastBean _northeast;
  SouthwestBean _southwest;

  NortheastBean get northeast => _northeast;
  SouthwestBean get southwest => _southwest;

  BoundsBean(this._northeast, this._southwest);

  BoundsBean.map(dynamic obj) {
    this._northeast = obj["northeast"];
    this._southwest = obj["southwest"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["northeast"] = _northeast;
    map["southwest"] = _southwest;
    return map;
  }

}

/// lat : 24.2991445
/// lng : 120.6090734

class SouthwestBean {
  double _lat;
  double _lng;

  double get lat => _lat;
  double get lng => _lng;

  SouthwestBean(this._lat, this._lng);

  SouthwestBean.map(dynamic obj) {
    this._lat = obj["lat"];
    this._lng = obj["lng"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["lat"] = _lat;
    map["lng"] = _lng;
    return map;
  }

}

/// lat : 24.3625505
/// lng : 120.7194453

class NortheastBean {
  double _lat;
  double _lng;

  double get lat => _lat;
  double get lng => _lng;

  NortheastBean(this._lat, this._lng);

  NortheastBean.map(dynamic obj) {
    this._lat = obj["lat"];
    this._lng = obj["lng"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["lat"] = _lat;
    map["lng"] = _lng;
    return map;
  }

}

/// long_name : "外埔區"
/// short_name : "外埔區"
/// types : ["administrative_area_level_3","political"]

class Address_componentsBean {
  String _longName;
  String _shortName;
  List<String> _types;

  String get longName => _longName;
  String get shortName => _shortName;
  List<String> get types => _types;

  Address_componentsBean(this._longName, this._shortName, this._types);

  Address_componentsBean.map(dynamic obj) {
    this._longName = obj["longName"];
    this._shortName = obj["shortName"];
    this._types = obj["types"];
  }

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map["longName"] = _longName;
    map["shortName"] = _shortName;
    map["types"] = _types;
    return map;
  }

}