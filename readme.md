# TileLink
Chisel library of TileLink, providing TileLink interconnect datatype declaration.

## Pending PRs
Philosophy of this repository is **fast break and fast fix**.
This repository always tracks remote developing branches, it may need some patches to work, `make patch` will append below in sequence:
<!-- BEGIN-PATCH -->
rocket-chip https://github.com/chipsalliance/rocket-chip/pull/2968  
rocket-chip https://github.com/chipsalliance/rocket-chip/pull/3013  
<!-- END-PATCH -->

## TODO
### BUSIP
These IPs will be ported from RocketChip, but work with the D/I of Chisel3.

### Diplomatic
It defines the diplomatic busip, which can be used for diplomatic connection.