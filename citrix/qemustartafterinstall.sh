qemu-system-x86_64 -cpu host -hda hda.qcow2 -enable-kvm -net nic -net user,hostfwd=tcp::22-:22,hostfwd=tcp::443-:443 -m 4096
