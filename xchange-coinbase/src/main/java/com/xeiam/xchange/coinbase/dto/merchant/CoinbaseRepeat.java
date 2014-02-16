package com.xeiam.xchange.coinbase.dto.merchant;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xeiam.xchange.coinbase.dto.EnumFromStringHelper;
import com.xeiam.xchange.coinbase.dto.EnumLowercaseJsonSerializer;
import com.xeiam.xchange.coinbase.dto.merchant.CoinbaseRepeat.CoinbaseRepeatDeserializer;

@JsonDeserialize(using = CoinbaseRepeatDeserializer.class)
@JsonSerialize(using = EnumLowercaseJsonSerializer.class)
public enum CoinbaseRepeat {

  NEVER, DAILY, WEEKLY, EVERY_TWO_WEEKS, MONTHLY, QUARTERLY, YEARLY;
  
  static class CoinbaseRepeatDeserializer extends JsonDeserializer<CoinbaseRepeat> {

    private static final EnumFromStringHelper<CoinbaseRepeat> FROM_STRING_HELPER = new EnumFromStringHelper<CoinbaseRepeat>(CoinbaseRepeat.class);

    @Override
    public CoinbaseRepeat deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

      ObjectCodec oc = jsonParser.getCodec();
      JsonNode node = oc.readTree(jsonParser);
      String jsonString = node.textValue();
      return FROM_STRING_HELPER.fromJsonString(jsonString);
    }
  }
}
