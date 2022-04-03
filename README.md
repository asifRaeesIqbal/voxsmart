# voxsmart

Simple application that parses dialled numbers into international numbers.

Assumptions:

Assuming all user numbers start with international dialing code.

Also Hong Kong has National Prefix of '0' but that was missing from the example table ? I added it assuming it was reqd.

Not sure why country code was a map between string and integer, this is wrong as barbados has a int'l code of "+1-246"?
So assuming these codes are not going to be used.

I would need to investigate whether the possibility fo clashing codes, becuase in NumberParser.findCountryCode() I currently pick the
very first match. It maybe that this is fine, but i would have test further.

Possible improvements:

I would also change the way the code works, utlising DI and spring to perhaps inject the map in via a config file. And have each
country represented by a class object. This would allow me to place some rules inside those objects.

