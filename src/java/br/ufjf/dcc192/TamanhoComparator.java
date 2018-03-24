package br.ufjf.dcc192;

class TamanhoComparator implements java.util.Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
    
}
