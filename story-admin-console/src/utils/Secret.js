var Base64 = require('js-base64').Base64; 
export default {
  encode(text){
    var result =Base64.encode(text);
    var encodeString = "";
    for(var i = result.length - 1; i >=0;i--){
      encodeString += result[i];
    }
    return encodeString;
  },
  decode(text){
    var result = "";
    for(var i = text.length - 1; i >=0;i--){
      result += text[i];
    }
    return Base64.decode(result);
  }
}

