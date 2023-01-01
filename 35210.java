class Dos {

    private static Name createNameInfo(Hashtable nameinfo) {
        Name subject = new Name();
        if (nameinfo.get(COUNTRY_KEY) != null && ((String) nameinfo.get(COUNTRY_KEY)).length() != 0) subject.addRDN(ObjectID.country, (String) nameinfo.get(COUNTRY_KEY));
        if (nameinfo.get(STATE_KEY) != null && ((String) nameinfo.get(STATE_KEY)).length() != 0) subject.addRDN(ObjectID.stateOrProvince, (String) nameinfo.get(STATE_KEY));
        if (nameinfo.get(CITY_KEY) != null && ((String) nameinfo.get(CITY_KEY)).length() != 0) subject.addRDN(ObjectID.locality, (String) nameinfo.get(CITY_KEY));
        if (nameinfo.get(ORGANIZATION_KEY) != null && ((String) nameinfo.get(ORGANIZATION_KEY)).length() != 0) subject.addRDN(ObjectID.organization, (String) nameinfo.get(ORGANIZATION_KEY));
        if (nameinfo.get(DEPARTMENT_KEY) != null && ((String) nameinfo.get(DEPARTMENT_KEY)).length() != 0) subject.addRDN(ObjectID.organizationalUnit, (String) nameinfo.get(DEPARTMENT_KEY));
        if (nameinfo.get(NAME_KEY) != null && ((String) nameinfo.get(NAME_KEY)).length() != 0) subject.addRDN(ObjectID.commonName, (String) nameinfo.get(NAME_KEY));
        if (nameinfo.get(EMAIL_KEY) != null && ((String) nameinfo.get(EMAIL_KEY)).length() != 0) {
            String bmps = new String((String) nameinfo.get(EMAIL_KEY));
            subject.addRDN(ObjectID.emailAddress, bmps);
        }
        return subject;
    }
}
