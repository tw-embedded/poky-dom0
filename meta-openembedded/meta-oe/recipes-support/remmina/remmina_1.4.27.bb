DESCRIPTION = "A feature rich Remote Desktop Application written in GTK+"
HOMEPAGE = "https://remmina.org"
SECTION = "Support"
LICENSE = "GPL-2.0-only & OpenSSL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dab7215512044d49037272ce1ac4ea8f file://LICENSE.OpenSSL;md5=c1eb3cee0a4dea27503c531267a69769"

DEPENDS = " \
    glib-2.0-native \
    openssl \
    freerdp \
    gtk+3 \
    gdk-pixbuf \
    atk \
    libgcrypt \
    libsodium \
    libssh \
    vte \
    json-glib \
    libsoup-2.4 \
    libvncserver \
    libsecret \
    libxkbfile \
"

DEPENDS:append:libc-musl = " libexecinfo"
LDFLAGS:append:libc-musl = " -lexecinfo"

SRCREV = "9d409a3556c8c2759a376b4f4ac5149db7aacf4f"
SRC_URI = "git://gitlab.com/Remmina/Remmina;protocol=https;branch=master"
S = "${WORKDIR}/git"

inherit cmake features_check mime mime-xdg gtk-icon-cache pkgconfig
REQUIRED_DISTRO_FEATURES = "x11"

EXTRA_OECMAKE += "-DWITH_APPINDICATOR=OFF -DWITH_GETTEXT=OFF -DWITH_TRANSLATIONS=OFF -DHAVE_LIBAPPINDICATOR=OFF -DWITH_WEBKIT2GTK=OFF"

PACKAGECONFIG[spice] = "-DWITH_SPICE=ON, -DWITH_SPICE=OFF, spice spice-protocol"
# Switch on gtk support in avahi recipe if you want to enable avahi support
PACKAGECONFIG[avahi] = "-DWITH_AVAHI=ON, -DWITH_AVAHI=OFF, avahi"

RDEPENDS:${PN} = "bash"

FILES:${PN}+= " \
    ${datadir}/metainfo \
    ${datadir}/mime \
"
